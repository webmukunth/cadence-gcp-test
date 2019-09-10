package com.anz.magneto.activites.enrich;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;

public interface EnrichActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5, taskList = Constants.TASK_LIST)
  WorkflowRequest enrich(WorkflowRequest workflowRequest);
}
