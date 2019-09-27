package com.anz.cadence.activities.sample2.limitcheck;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface LimitCheckActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 60, taskList = Constants.TASK_LIST_LIMIT_CHECK)
  @NonNull LimitCheckOutcome limitCheck(@NonNull WorkflowRequest workflowRequest);
}
