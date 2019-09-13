package com.anz.magneto.activites.enrich;

import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnrichActivityImpl implements EnrichActivity {

  @Override
  public @NonNull WorkflowRequest enrich(WorkflowRequest workflowRequest) {
    log.info("Enriching {}", workflowRequest);
    return workflowRequest;
  }
}
