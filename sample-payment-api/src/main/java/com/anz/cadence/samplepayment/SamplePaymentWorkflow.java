package com.anz.cadence.samplepayment;

import com.anz.cadence.activities.accounting.AccountingResponse;
import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.anz.cadence.commons.api.workflow.WorkflowResponse;
import com.uber.cadence.workflow.QueryMethod;
import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;
import lombok.NonNull;

public interface SamplePaymentWorkflow {

  /* Wait for 3 days + 60 seconds */
  @WorkflowMethod(taskList = Constants.TASK_LIST, executionStartToCloseTimeoutSeconds = 259260)
  WorkflowResponse submitPayment(@NonNull WorkflowRequest request);

  @SignalMethod
  void stopProcessPayment();

  @SignalMethod
  void releaseFraudCheckHold();

  @SignalMethod
  void paymentCleared();

  @QueryMethod
  @NonNull AccountingResponse getCustomerDebitResponse();

}
