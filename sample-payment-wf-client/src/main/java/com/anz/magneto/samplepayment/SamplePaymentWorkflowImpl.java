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
    fraudCheckActivity = Workflow
        .newActivityStub(FraudCheckActivity.class, new ActivityOptions.Builder()
            .setScheduleToCloseTimeout(Duration.ofSeconds(120))
            .build());
  }

  @Override
  public WorkflowResponse processPayment(WorkflowRequest request) {

    var response = validateActivity.validate(request);
    if (response.isError()) {
      return response;
    }
    log.debug("Done with validate: {}", response);

    enrichActivity.enrich(request);
    log.debug("Done with enrich ");

    var debitCustomerCreditFloatResponse = accountingActivity.debitCustomerCreditFloat(request);

    if (debitCustomerCreditFloatResponse.isError()) {
      return WorkflowResponse.builder()
          .status(debitCustomerCreditFloatResponse.getStatus())
          .build();
    }
    log.debug("Done with accounting");

    enrichActivity.enrich(request);
    log.debug("Done with enrich");

    WorkflowResponse fraudResponse;
    try {
      fraudResponse = fraudCheckActivity.fraudCheck(request);
      log.debug("Done with fraudCheck");
    } catch (ActivityTimeoutException t) {
      log.debug("Fraud timeout, continue with success");
      fraudResponse = WorkflowResponse.builder().status(Status.SUCCESS).build();
    }

    log.debug("Done with workflow {}", fraudResponse);

    return null;
  }
}
