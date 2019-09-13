package com.anz.magneto.samplepayment;

import com.anz.magneto.activites.accounting.AccountingActivity;
import com.anz.magneto.activites.accounting.AccountingResponse;
import com.anz.magneto.activites.accounting.AccountingStatus;
import com.anz.magneto.activites.clientresponse.ClientResponseActivity;
import com.anz.magneto.activites.enrich.EnrichActivity;
import com.anz.magneto.activites.fraudcheck.FraudCheckActivity;
import com.anz.magneto.activites.fraudcheck.FraudCheckOutcome;
import com.anz.magneto.activites.limitcheck.LimitCheckActivity;
import com.anz.magneto.activites.limitcheck.LimitCheckOutcome;
import com.anz.magneto.activites.validate.ValidateActivity;
import com.anz.magneto.commons.api.workflow.LimitType;
import com.anz.magneto.commons.api.workflow.StopWorkflowException;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.anz.magneto.commons.api.workflow.WorkflowResponse;
import com.anz.magneto.commons.api.workflow.WorkflowStatus;
import com.uber.cadence.workflow.ActivityTimeoutException;
import com.uber.cadence.workflow.Saga;
import com.uber.cadence.workflow.Workflow;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SamplePaymentWorkflowImpl implements SamplePaymentWorkflow {

  final private ValidateActivity validateActivity;
  final private EnrichActivity enrichActivity;
  final private AccountingActivity accountingActivity;
  final private FraudCheckActivity fraudCheckActivity;
  final private LimitCheckActivity limitCheckActivity;
  final private ClientResponseActivity clientResponseActivity;
  final private Saga saga;

  private boolean stopProcessPayment = false;
  private boolean releaseFraudCheckHold = false;

  private AccountingResponse customerDebitResponse;

  public SamplePaymentWorkflowImpl() {
    validateActivity = Workflow.newActivityStub(ValidateActivity.class);
    enrichActivity = Workflow.newActivityStub(EnrichActivity.class);
    accountingActivity = Workflow.newActivityStub(AccountingActivity.class);
    limitCheckActivity = Workflow.newActivityStub(LimitCheckActivity.class);
    clientResponseActivity = Workflow.newActivityStub(ClientResponseActivity.class);
    fraudCheckActivity = Workflow.newActivityStub(FraudCheckActivity.class);
    var sagaOpt = new Saga.Options.Builder()
        .setContinueWithError(true)
        .setParallelCompensation(false)
        .build();
    saga = new Saga(sagaOpt);
  }

  @Override
  public void submitPayment(WorkflowRequest request) {
    var response = doProcessPayment(request);
    clientResponseActivity.sendResponse(request, response);
  }

  private WorkflowResponse doProcessPayment(WorkflowRequest r) {

    WorkflowRequest request = r;

    /* Validate payment request */
    var validationErrors = validateActivity.validate(request);
    if (validationErrors != null && validationErrors.size() > 0) {
      /* Send validation errors to client */
      return new WorkflowResponse(WorkflowStatus.ERROR, "Validation failed", validationErrors);
    }

    /* Enrich payment request */
    request = enrichActivity.enrich(request);
    if (stopProcessPayment) {
      throw new StopWorkflowException("Stopped after enrich");
    }

    /* Debit customer */
    var accountingStatus = debitCustomer(request);
    if (accountingStatus != AccountingStatus.SUCCESS) {
      return new WorkflowResponse(WorkflowStatus.ERROR, "debitCustomer failed " + accountingStatus);
    }
    if (stopProcessPayment) {
      throw new StopWorkflowException("Stopped after debitCustomer");
    }

    /* Add customer debit to Saga for compensation */
    saga.addCompensation(accountingActivity::reverseDebitCustomerCreditFloat, request,
        customerDebitResponse);

    var fraudCheckOutcome = fraudCheck(request);

    if (stopProcessPayment || fraudCheckOutcome == FraudCheckOutcome.FAIL) {
      throw new StopWorkflowException("Stopped after fraudcheck due to stopProcessPayment: "
          + stopProcessPayment + " or fraudCheckOutcome: " + fraudCheckOutcome);
    }

    return new WorkflowResponse(WorkflowStatus.SUCCESS, "SUCCESS");
  }

  private FraudCheckOutcome fraudCheck(WorkflowRequest request) {
    try {
      var ret = fraudCheckActivity.fraudCheck(request);
      /* for hold response from fraud check, wait for another 8 hours */
      if (ret == FraudCheckOutcome.HOLD) {
        if (!Workflow.await(
            Duration.ofHours(8), () -> releaseFraudCheckHold || stopProcessPayment)) {
          /* no response for last 8 hours means fail */
          ret = FraudCheckOutcome.FAIL;
        } else if (releaseFraudCheckHold) {
          /* Got signal to release fraud check hold, means good to proceed */
          ret = FraudCheckOutcome.PASS;
        } else {
          /* No signal means fail fraud check */
          ret = FraudCheckOutcome.FAIL;
        }
      }
      return ret;
    } catch (ActivityTimeoutException e) {
      /* consider timeout as pass */
      return FraudCheckOutcome.PASS;
    }
  }

  private AccountingStatus debitCustomer(WorkflowRequest request) {

    if (request.getLimitType() == LimitType.LIMITONLY) {
      /* If the limit type is 'Limit Only', check it first */
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

  @Override
  public void stopProcessPayment() {
    stopProcessPayment = true;
  }

  @Override
  public void releaseFraudCheckHold() {
    releaseFraudCheckHold = true;
  }

  @Override
  public AccountingResponse getCustomerDebitResponse() {
    return customerDebitResponse;
  }
}
