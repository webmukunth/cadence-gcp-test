package com.anz.cadence.activities.validate;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.ValidationError;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.ActivityMethod;
import java.util.List;
import javax.annotation.Nullable;
import lombok.NonNull;

public interface ValidateActivity {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5, taskList = Constants.TASK_LIST)
  @Nullable
  List<ValidationError> validate(@NonNull WorkflowRequest workflowRequest);

}