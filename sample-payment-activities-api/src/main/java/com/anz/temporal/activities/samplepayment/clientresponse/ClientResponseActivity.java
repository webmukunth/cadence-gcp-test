package com.anz.temporal.activities.samplepayment.clientresponse;


import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import com.anz.temporal.commons.api.workflow.WorkflowResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import lombok.NonNull;

@ActivityInterface
public interface ClientResponseActivity {

  @ActivityMethod
  void sendResponse(@NonNull WorkflowRequest request, @NonNull WorkflowResponse response);
}
