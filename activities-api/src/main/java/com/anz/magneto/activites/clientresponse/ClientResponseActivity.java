package com.anz.magneto.activites.clientresponse;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.anz.magneto.commons.api.workflow.WorkflowResponse;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface ClientResponseActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5, taskList = Constants.TASK_LIST)
  void sendResponse(@NonNull WorkflowRequest request, @NonNull WorkflowResponse response);
}
