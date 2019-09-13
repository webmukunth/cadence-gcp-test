package com.anz.magneto.activites.validate;

import com.anz.magneto.commons.api.workflow.ValidationError;
import com.anz.magneto.commons.api.workflow.ValidationErrors;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import java.util.List;
import javax.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateActivityImpl implements ValidateActivity {

  @Override
  public @Nullable
  List<ValidationError> validate(WorkflowRequest workflowRequest) {
    log.info("validate: {}", workflowRequest);
    return ValidationErrors.builder()
        .error(new ValidationError("c1", "Testing 1"))
        .error(new ValidationError("c2", "Testing 2"))
        .build()
        .getErrors();
  }
}
