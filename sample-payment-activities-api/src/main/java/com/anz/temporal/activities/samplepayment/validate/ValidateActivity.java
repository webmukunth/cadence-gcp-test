package com.anz.temporal.activities.samplepayment.validate;

import com.anz.temporal.commons.api.workflow.ValidationError;
import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

@ActivityInterface
public interface ValidateActivity {

  @ActivityMethod
  Optional<List<ValidationError>> validate(@NonNull WorkflowRequest workflowRequest);

}