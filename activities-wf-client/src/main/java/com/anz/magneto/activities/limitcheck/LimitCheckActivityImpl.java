package com.anz.magneto.activities.limitcheck;

import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import javax.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LimitCheckActivityImpl implements LimitCheckActivity {

  @Override
  public @NonNull LimitCheckOutcome limitCheck(@NotNull WorkflowRequest workflowRequest) {
    log.info("limitCheck: {}", workflowRequest);
    return LimitCheckOutcome.PASS;
  }
}
