package com.anz.magneto.activites.fraudcheck;

import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.activity.Activity;
import javax.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FraudCheckActivityImpl implements FraudCheckActivity {

  @Override
  public @NonNull FraudCheckOutcome fraudCheck(@NotNull WorkflowRequest workflowRequest) {
    log.info("fraudCheck: {}", workflowRequest);
    Activity.doNotCompleteOnReturn();
    return null;
  }
}
