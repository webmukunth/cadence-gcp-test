package com.anz.magneto.activites.limitcheck;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface LimitCheckActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5, taskList = Constants.TASK_LIST)
  @NonNull LimitCheckOutcome limitCheck(@NonNull WorkflowRequest workflowRequest);
}
