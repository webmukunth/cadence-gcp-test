package com.anz.magneto.activites.validate;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.WorkflowRequest;
import com.anz.magneto.commons.api.WorkflowResponse;
import com.uber.cadence.activity.ActivityMethod;

public interface ValidateActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5, taskList = Constants.TASK_LIST)
  WorkflowResponse validate(WorkflowRequest workflowRequest);
}
