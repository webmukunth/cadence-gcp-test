package com.anz.magneto.activites.fraudcheck;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.WorkflowRequest;
import com.anz.magneto.commons.api.WorkflowResponse;
import com.uber.cadence.activity.ActivityMethod;

public interface FraudCheckActivity {

  @ActivityMethod(taskList = Constants.TASK_LIST)
  WorkflowResponse fraudCheck(WorkflowRequest workflowRequest);
}
