package com.anz.temporal.activities.samplepayment.enrich;

import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import lombok.NonNull;

@ActivityInterface
public interface EnrichActivity {

  @ActivityMethod
  @NonNull WorkflowRequest enrich(@NonNull WorkflowRequest workflowRequest);
}
