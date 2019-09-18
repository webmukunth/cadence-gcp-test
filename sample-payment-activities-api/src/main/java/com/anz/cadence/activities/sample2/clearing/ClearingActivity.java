package com.anz.cadence.activities.sample2.clearing;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface ClearingActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5, taskList = Constants.TASK_LIST)
  @NonNull ClearingResponse clearPayment(@NonNull WorkflowRequest request);
}
