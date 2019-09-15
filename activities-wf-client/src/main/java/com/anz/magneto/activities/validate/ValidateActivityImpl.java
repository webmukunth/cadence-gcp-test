package com.anz.magneto.activities.validate;

import com.anz.magneto.commons.api.workflow.ValidationError;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import java.util.List;
import javax.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ValidateActivityImpl implements ValidateActivity {

  @Override
  public @Nullable
  List<ValidationError> validate(WorkflowRequest workflowRequest) {
    log.info("validate: {}", workflowRequest);
    return null;
    /*return ValidationErrors.builder()
        .error(new ValidationError("c1", "Testing 1"))
        .error(new ValidationError("c2", "Testing 2"))
        .build()
        .getErrors(); */
  }
}
