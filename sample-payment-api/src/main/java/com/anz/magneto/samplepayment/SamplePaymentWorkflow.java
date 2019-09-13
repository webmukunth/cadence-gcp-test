package com.anz.magneto.samplepayment;

import com.anz.magneto.activites.accounting.AccountingResponse;
import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.workflow.QueryMethod;
import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;
import lombok.NonNull;

public interface SamplePaymentWorkflow {

  @WorkflowMethod(taskList = Constants.TASK_LIST, executionStartToCloseTimeoutSeconds = 601)
  void submitPayment(@NonNull WorkflowRequest request);

  @SignalMethod
  void stopProcessPayment();

  @SignalMethod
  void releaseFraudCheckHold();

  @QueryMethod
  @NonNull AccountingResponse getCustomerDebitResponse();

}
