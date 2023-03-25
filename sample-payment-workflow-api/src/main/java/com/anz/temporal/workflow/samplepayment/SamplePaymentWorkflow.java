package com.anz.temporal.workflow.samplepayment;

import com.anz.temporal.activities.samplepayment.accounting.AccountingResponse;
import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import com.anz.temporal.commons.api.workflow.WorkflowResponse;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import lombok.NonNull;

@WorkflowInterface
public interface SamplePaymentWorkflow {

  /* Wait for 3 days + 60 seconds */
  @WorkflowMethod/*(taskList = Constants.TASK_QUEUE, executionStartToCloseTimeoutSeconds = 259260)*/
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
