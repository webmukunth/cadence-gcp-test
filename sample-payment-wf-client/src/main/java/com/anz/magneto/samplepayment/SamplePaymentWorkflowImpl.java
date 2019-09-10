package com.anz.magneto.samplepayment;

import com.anz.magneto.activites.accounting.AccountingActivity;
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

    var debitCustomerCreditFloatResponse =
        accountingActivity.debitCustomerCreditFloat(enrichedRequest);

    if (debitCustomerCreditFloatResponse.isError()) {
      return WorkflowResponse.builder()
          .status(debitCustomerCreditFloatResponse.getStatus())
          .build();
    }

    try {
      response = fraudCheckActivity.fraudCheck(enrichedRequest);
    } catch (ActivityTimeoutException t) {
      log.debug("Fraud timeout, continue with success");
      response = WorkflowResponse.builder().message("Fraud timedout").status(Status.SUCCESS)
          .build();
    }

    log.info("Final response {}", response);

    return response;
  }
}
