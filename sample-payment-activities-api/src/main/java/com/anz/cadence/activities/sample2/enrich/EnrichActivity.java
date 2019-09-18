package com.anz.cadence.activities.sample2.enrich;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface EnrichActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5, taskList = Constants.TASK_LIST)
  @NonNull WorkflowRequest enrich(@NonNull WorkflowRequest workflowRequest);
}
