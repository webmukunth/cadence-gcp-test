package com.anz.cadence.activities.samplepayment.fraudcheck;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import lombok.NonNull;

public interface FraudCheckActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 240, taskList = Constants.TASK_LIST_FRAUD_CHECK)
  @NonNull FraudCheckOutcome fraudCheck(@NonNull WorkflowRequest workflowRequest);

}
