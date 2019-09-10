package com.anz.magneto.samplepayment;

import com.anz.magneto.activites.accounting.AccountingResponse;
import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.WorkflowRequest;
import com.anz.magneto.commons.api.WorkflowResponse;
import com.uber.cadence.workflow.QueryMethod;
import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;

public interface SamplePaymentWorkflow {

  @WorkflowMethod(taskList = Constants.TASK_LIST, executionStartToCloseTimeoutSeconds = 601)
  WorkflowResponse processPayment(WorkflowRequest request);

  @SignalMethod
  void stopProcessPayment();

  @QueryMethod
  AccountingResponse getCustomerDebitResponse();

}
