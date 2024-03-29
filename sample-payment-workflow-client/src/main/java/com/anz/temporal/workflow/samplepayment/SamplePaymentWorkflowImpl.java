package com.anz.temporal.workflow.samplepayment;

import com.anz.temporal.activities.samplepayment.accounting.AccountingActivity;
import com.anz.temporal.activities.samplepayment.accounting.AccountingResponse;
import com.anz.temporal.activities.samplepayment.accounting.AccountingStatus;
import com.anz.temporal.activities.samplepayment.clearing.ClearingActivity;
import com.anz.temporal.activities.samplepayment.clearing.ClearingResponse;
import com.anz.temporal.activities.samplepayment.clearing.ClearingStatus;
import com.anz.temporal.activities.samplepayment.clientresponse.ClientResponseActivity;
import com.anz.temporal.activities.samplepayment.enrich.EnrichActivity;
import com.anz.temporal.activities.samplepayment.fraudcheck.FraudCheckActivity;
import com.anz.temporal.activities.samplepayment.fraudcheck.FraudCheckOutcome;
import com.anz.temporal.activities.samplepayment.limitcheck.LimitCheckActivity;
import com.anz.temporal.activities.samplepayment.limitcheck.LimitCheckOutcome;
import com.anz.temporal.activities.samplepayment.validate.ValidateActivity;
import com.anz.temporal.commons.api.workflow.LimitType;
import com.anz.temporal.commons.api.workflow.StopWorkflowException;
import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import com.anz.temporal.commons.api.workflow.WorkflowResponse;
import com.anz.temporal.commons.api.workflow.WorkflowStatus;
import com.anz.temporal.commons.utils.TraceUtil;
import io.temporal.activity.ActivityOptions;
import io.temporal.client.WorkflowException;
import io.temporal.failure.ActivityFailure;
import io.temporal.failure.TimeoutFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SamplePaymentWorkflowImpl implements SamplePaymentWorkflow {

  final private ValidateActivity validateActivity;
  final private EnrichActivity enrichActivity;
  final private AccountingActivity accountingActivity;
  final private FraudCheckActivity fraudCheckActivity;
  final private LimitCheckActivity limitCheckActivity;
  final private ClearingActivity clearingActivity;
  final private ClientResponseActivity clientResponseActivity;
  final private Saga saga;
  final private TraceUtil traceUtil;

  private boolean stopProcessPayment = false;
  private boolean releaseFraudCheckHold = false;
  private boolean paymentCleared = false;

  private AccountingResponse customerDebitResponse;
  private ClearingResponse clearingResponse;

  public SamplePaymentWorkflowImpl() {
   ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            // Timeout options specify when to automatically timeout Activities if the process is taking too long.
            .setStartToCloseTimeout(Duration.ofDays(3))
            .setScheduleToCloseTimeout(Duration.ofHours(4))
            .build();
    validateActivity = Workflow.newActivityStub(ValidateActivity.class,defaultActivityOptions);
    enrichActivity = Workflow.newActivityStub(EnrichActivity.class,defaultActivityOptions);
    accountingActivity = Workflow.newActivityStub(AccountingActivity.class,defaultActivityOptions);
    limitCheckActivity = Workflow.newActivityStub(LimitCheckActivity.class,defaultActivityOptions);
    clientResponseActivity = Workflow.newActivityStub(ClientResponseActivity.class,defaultActivityOptions);
    clearingActivity = Workflow.newActivityStub(ClearingActivity.class,defaultActivityOptions);
    fraudCheckActivity = Workflow.newActivityStub(FraudCheckActivity.class,defaultActivityOptions);

    var sagaOpt = new Saga.Options.Builder()
        .setContinueWithError(true)
        .setParallelCompensation(false)
        .build();
    saga = new Saga(sagaOpt);
    traceUtil = TraceUtil.getGlobalTraceUtil();
  }

  @Override
  public WorkflowResponse submitPayment(WorkflowRequest request) {
    log.debug("submitPayment: {}", request);
    var response = doProcessPayment(request);
    clientResponseActivity.sendResponse(request, response);
    log.debug("response: {}", response);
    traceUtil.recordDuration("workflow_request",
        request.getRequestDateTime(), response.getResponseDateTime(),
        "workflow", "SamplePaymentWorkflow",
        "client", request.getClientName()
    );
    return response;
  }

  private WorkflowResponse doProcessPayment(WorkflowRequest r) {

    WorkflowRequest request = r;

    /* Validate payment request */
    var validationErrors = validateActivity.validate(request);

    if (validationErrors.isPresent()) {
      /* Send validation errors to client */
      return WorkflowResponse.customBuilder(r, WorkflowStatus.ERROR)
          .message("Validation failed")
          .validationErrors(validationErrors)
          .build();
    }
    log.debug("validateActivity completed");

    /* Enrich payment request */
    request = enrichActivity.enrich(request);
    if (stopProcessPayment) {
      throw new StopWorkflowException("Stopped after enrich");
    }
    log.debug("enrichActivity completed");

    /* Debit customer */
    var accountingStatus = debitCustomer(request);
    if (accountingStatus != AccountingStatus.SUCCESS) {
      return WorkflowResponse.customBuilder(request, WorkflowStatus.ERROR)
          .message("debitCustomer failed " + accountingStatus)
          .build();
    }
    if (stopProcessPayment) {
      throw new StopWorkflowException("Stopped after debitCustomer");
    }
    log.debug("debitCustomer completed");

    /* Add customer debit to Saga for compensation */
    saga.addCompensation(accountingActivity::reverseDebitCustomerCreditFloat, request,
        customerDebitResponse);

    /* Fraud check */
    var fraudCheckOutcome = fraudCheck(request);

    if (fraudCheckOutcome != FraudCheckOutcome.PASS) {
      throw new StopWorkflowException("Stopped due to fraudCheckOutcome: " + fraudCheckOutcome);
    }
    if (stopProcessPayment) {
      throw new StopWorkflowException("Stopped after fraudCheck");
    }
    log.debug("fraudCheck completed");

    /* Clearing */
    var clearingStatus = clearPayment(request);

    if (clearingStatus == ClearingStatus.REJECTED) {
      log.info("About to compensate {}", saga);
      saga.compensate();
      throw new StopWorkflowException("Stopped due to clearingStatus: " + clearingStatus);
    }
    if (stopProcessPayment) {
      throw new StopWorkflowException("Stopped after clearing");
    }
    log.debug("clearPayment completed");

    return WorkflowResponse.customBuilder(request, WorkflowStatus.SUCCESS)
        .message("SUCCESS")
        .build();
  }

  /**
   * Fraud Check
   */
  private FraudCheckOutcome fraudCheck(WorkflowRequest request) {
    try {
      var ret = fraudCheckActivity.fraudCheck(request);
      /* for hold response from fraud check, wait for another 8 hours */
      if (ret == FraudCheckOutcome.HOLD) {
        log.info("Waiting for fraudCheck signal");
        if (!Workflow.await(
            Duration.ofHours(8), () -> releaseFraudCheckHold || stopProcessPayment)) {
          log.warn("Fraud check hold timed out");
          /* no response for last 8 hours means fail */
          return FraudCheckOutcome.HOLD_TIMEOUT;
        }
        if (releaseFraudCheckHold) {
          /* Got signal to release fraud check hold, means good to proceed */
          return FraudCheckOutcome.PASS;
        }
      }
      return ret;
    } catch (ActivityFailure e) {
      /* consider timeout as pass */
      return FraudCheckOutcome.PASS;
    }
  }

  /**
   * Debit Customer
   */
  private AccountingStatus debitCustomer(WorkflowRequest request) {

    if (request.getLimitType() == LimitType.LIMITONLY) {
      /* check limit first */
      if (limitCheckActivity.limitCheck(request) == LimitCheckOutcome.FAIL) {
        /* No available limit */
        return AccountingStatus.INSUFFICIENT_LIMIT;
      }
    }

    customerDebitResponse = accountingActivity.debitCustomerCreditFloat(request);

    if (customerDebitResponse.getStatus() == AccountingStatus.INSUFFICIENT_BALANCE
        && request.getLimitType() == LimitType.AFPTHENLIMIT) {

      /* Insufficient balance. If the limit type is AFPTHENLIMIT - Available found and then limit */

      if (limitCheckActivity.limitCheck(request) == LimitCheckOutcome.PASS) {
        /* Force debit if limit check pass */
        customerDebitResponse = accountingActivity.forceDebitCustomerCreditFloat(request);
      } else {
        return AccountingStatus.INSUFFICIENT_LIMIT;
      }
    }

    return customerDebitResponse.getStatus();
  }

  private ClearingStatus clearPayment(WorkflowRequest request) {

    try {
      clearingResponse = clearingActivity.clearPayment(request);

      /* If the status is submitted, wait for signal */
      if (clearingResponse.getStatus() == ClearingStatus.SUBMITTED) {
        log.info("Waiting for clearing signal");
        if (!Workflow.await(
                Duration.ofDays(3), () -> (paymentCleared || stopProcessPayment))) {
          /* timeout */
          log.warn("No response from external clearing system");
          clearingResponse = clearingResponse.withStatus(ClearingStatus.CLEARED);
        } else if (paymentCleared) {
          clearingResponse = clearingResponse.withStatus(ClearingStatus.CLEARED);
        }
      }
      return clearingResponse.getStatus();
    }catch(ActivityFailure e){
      /* Time out as Cleared */
      return clearingResponse.withStatus(ClearingStatus.CLEARED).getStatus();
    }
  }

  @Override
  public void stopProcessPayment() {
    log.info("stopProcessPayment");
    stopProcessPayment = true;
  }

  @Override
  public void releaseFraudCheckHold() {
    log.info("releaseFraudCheckHold");
    releaseFraudCheckHold = true;
  }

  @Override
  public void paymentCleared() {
    log.info("paymentCleared");
    paymentCleared = true;
  }

  @Override
  public AccountingResponse getCustomerDebitResponse() {
    return customerDebitResponse;
  }
}
