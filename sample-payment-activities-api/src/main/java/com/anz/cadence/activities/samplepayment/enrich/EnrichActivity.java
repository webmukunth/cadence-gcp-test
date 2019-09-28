package com.anz.cadence.activities.samplepayment.enrich;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface EnrichActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 60, taskList = Constants.TASK_LIST_ENRICH)
  @NonNull WorkflowRequest enrich(@NonNull WorkflowRequest workflowRequest);
}
