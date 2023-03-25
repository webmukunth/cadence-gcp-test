package com.anz.temporal.workflow.sample2;

import com.anz.temporal.activities.sample2.ServiceBActivity.DSResponse;
import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import com.anz.temporal.commons.api.workflow.WorkflowResponse;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import lombok.NonNull;

@WorkflowInterface
public interface Sample2Workflow {

  @WorkflowMethod/*(taskList = Constants.TASK_QUEUE, executionStartToCloseTimeoutSeconds = 10)*/
  WorkflowResponse execute(@NonNull WorkflowRequest request, boolean responseInOrder);

  /* Service B Response 6 */
  @SignalMethod
  void response6(@NonNull Boolean quarantine);

  /* Service B Response 7 */
  @SignalMethod
  void response7(@NonNull DSResponse response);
}
