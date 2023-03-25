package com.anz.temporal.activities.samplepayment.clearing;

import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import lombok.NonNull;

@ActivityInterface
public interface ClearingActivity {

  @ActivityMethod
  @NonNull ClearingResponse clearPayment(@NonNull WorkflowRequest request);
}
