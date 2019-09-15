package com.anz.magneto.activities.validate;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.workflow.ValidationError;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import java.util.List;
import javax.annotation.Nullable;
import lombok.NonNull;

public interface ValidateActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5, taskList = Constants.TASK_LIST)
  @Nullable
  List<ValidationError> validate(@NonNull WorkflowRequest workflowRequest);

}