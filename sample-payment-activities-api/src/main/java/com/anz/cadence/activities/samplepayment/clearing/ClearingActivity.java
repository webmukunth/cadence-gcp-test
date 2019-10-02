package com.anz.cadence.activities.samplepayment.clearing;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface ClearingActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 60, taskList = Constants.TASK_LIST)
  @NonNull ClearingResponse clearPayment(@NonNull WorkflowRequest request);
}
