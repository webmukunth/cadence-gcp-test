package com.anz.magneto.activites.fraudcheck;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface FraudCheckActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 240, taskList = Constants.TASK_LIST)
  @NonNull FraudCheckOutcome fraudCheck(@NonNull WorkflowRequest workflowRequest);

}
