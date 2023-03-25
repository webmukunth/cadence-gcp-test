package com.anz.temporal.activities.samplepayment.limitcheck;

import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import lombok.NonNull;

@ActivityInterface
public interface LimitCheckActivity {

  @ActivityMethod
  @NonNull LimitCheckOutcome limitCheck(@NonNull WorkflowRequest workflowRequest);
}
