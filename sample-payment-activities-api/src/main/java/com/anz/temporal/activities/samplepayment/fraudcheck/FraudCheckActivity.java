package com.anz.temporal.activities.samplepayment.fraudcheck;

import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import lombok.NonNull;

@ActivityInterface
public interface FraudCheckActivity {

  @ActivityMethod
  @NonNull FraudCheckOutcome fraudCheck(@NonNull WorkflowRequest workflowRequest);

}
