package com.anz.magneto.samplepayment;

import com.anz.magneto.activites.accounting.AccountingActivity;
import com.anz.magneto.activites.accounting.AccountingResponse;
import com.anz.magneto.activites.enrich.EnrichActivity;
import com.anz.magneto.activites.fraudcheck.FraudCheckActivity;
import com.anz.magneto.activites.validate.ValidateActivity;
import com.anz.magneto.commons.api.Status;
import com.anz.magneto.commons.api.WorkflowRequest;
import com.anz.magneto.commons.api.WorkflowResponse;
import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.ActivityTimeoutException;
import com.uber.cadence.workflow.Workflow;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SamplePaymentWorkflowImpl implements SamplePaymentWorkflow {

  final private ValidateActivity validateActivity;
  final private EnrichActivity enrichActivity;
  final private AccountingActivity accountingActivity;
  final private FraudCheckActivity fraudCheckActivity;

  private boolean stopProcessPayment = false;
  private AccountingResponse customerDebitResponse;

  public SamplePaymentWorkflowImpl() {
    validateActivity = Workflow.newActivityStub(ValidateActivity.class);
    enrichActivity = Workflow.newActivityStub(EnrichActivity.class);
    accountingActivity = Workflow.newActivityStub(AccountingActivity.class);
    fraudCheckActivity = Workflow.newActivityStub(FraudCheckActivity.class,
        new ActivityOptions.Builder().setScheduleToCloseTimeout(Duration.ofSeconds(120)).build());
  }

  @Override
  public WorkflowResponse processPayment(WorkflowRequest request) {

    var response = validateActivity.validate(request);
    if (response.isError()) {
      return response;
    }

    var enrichedRequest = enrichActivity.enrich(request);

    if (stopProcessPayment) {
      log.warn("processPayment stopped after enrich");
      return WorkflowResponse.builder().status(Status.STOPPED)
          .message("Workflow stopped after enrich").build();
    }

    customerDebitResponse = accountingActivity.debitCustomerCreditFloat(enrichedRequest);

    if (customerDebitResponse.isError()) {
      return WorkflowResponse.builder().status(customerDebitResponse.getStatus()).build();
    }

    if (stopProcessPayment) {
      log.warn("processPayment stopped after debitCustomerCreditFloat");
      return WorkflowResponse.builder()
          .status(Status.STOPPED)
          .message("Workflow stopped after debitCustomerCreditFloat")
          .build();
    }

    try {
      response = fraudCheckActivity.fraudCheck(enrichedRequest);
    } catch (ActivityTimeoutException t) {
      log.debug("Fraud timeout, continue with success");
      response = WorkflowResponse.builder()
          .message("Fraud timedout")
          .status(Status.SUCCESS)
          .build();
    }

    if (stopProcessPayment) {
      log.warn("processPayment stopped after fraud check");
      return WorkflowResponse.builder()
          .status(Status.STOPPED)
          .message("Workflow stopped after fraudCheck")
          .build();
    }

    log.info("Final response {}", response);

    return response;
  }

  @Override
  public void stopProcessPayment() {
    log.debug("About to stop payment processing");
    stopProcessPayment = true;
  }

  @Override
  public AccountingResponse getCustomerDebitResponse() {
    return customerDebitResponse;
  }
}
