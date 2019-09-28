package com.anz.cadence.workflow.samplepayment;

import com.anz.cadence.activities.samplepayment.ServiceBActivity.DSResponse;
import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.anz.cadence.commons.api.workflow.WorkflowResponse;
import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;
import lombok.NonNull;

public interface Sample2Workflow {

  @WorkflowMethod(taskList = Constants.TASK_LIST, executionStartToCloseTimeoutSeconds = 10)
  WorkflowResponse execute(@NonNull WorkflowRequest request, boolean responseInOrder);

  /* Service B Response 6 */
  @SignalMethod
  void response6(@NonNull Boolean quarantine);

  /* Service B Response 7 */
  @SignalMethod
  void response7(@NonNull DSResponse response);
}
