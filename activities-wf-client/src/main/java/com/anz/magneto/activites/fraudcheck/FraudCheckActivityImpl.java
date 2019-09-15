package com.anz.magneto.activites.fraudcheck;

import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import javax.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FraudCheckActivityImpl implements FraudCheckActivity {

  @Override
  public @NonNull FraudCheckOutcome fraudCheck(@NotNull WorkflowRequest workflowRequest) {
    log.info("fraudCheck: {}", workflowRequest);
    //Activity.doNotCompleteOnReturn();
    return FraudCheckOutcome.PASS;
  }
}
