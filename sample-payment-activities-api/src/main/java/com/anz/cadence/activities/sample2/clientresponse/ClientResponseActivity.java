package com.anz.cadence.activities.sample2.clientresponse;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.anz.cadence.commons.api.workflow.WorkflowResponse;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface ClientResponseActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 60, taskList = Constants.TASK_LIST)
  void sendResponse(@NonNull WorkflowRequest request, @NonNull WorkflowResponse response);
}
