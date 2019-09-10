package com.anz.magneto.activites.validate;

import com.anz.magneto.commons.api.Status;
import com.anz.magneto.commons.api.WorkflowRequest;
import com.anz.magneto.commons.api.WorkflowResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateActivityImpl implements ValidateActivity {

  @Override
  public WorkflowResponse validate(WorkflowRequest workflowRequest) {
    log.info("validate: {}", workflowRequest);
    return WorkflowResponse.builder().status(Status.SUCCESS).build();
  }
}
