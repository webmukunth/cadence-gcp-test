package com.anz.magneto.activites.enrich;

import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EnrichActivityImpl implements EnrichActivity {

  @Override
  public @NonNull WorkflowRequest enrich(WorkflowRequest workflowRequest) {
    log.info("Enriching {}", workflowRequest);
    return workflowRequest;
  }
}
