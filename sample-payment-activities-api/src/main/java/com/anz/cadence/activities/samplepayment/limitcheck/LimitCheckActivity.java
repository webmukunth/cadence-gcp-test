package com.anz.cadence.activities.samplepayment.limitcheck;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface LimitCheckActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 60, taskList = Constants.TASK_LIST)
  @NonNull LimitCheckOutcome limitCheck(@NonNull WorkflowRequest workflowRequest);
}
