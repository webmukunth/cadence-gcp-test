package com.anz.magneto.activites.enrich;

import com.anz.magneto.commons.api.WorkflowRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnrichActivityImpl implements EnrichActivity {

  @Override
  public WorkflowRequest enrich(WorkflowRequest workflowRequest) {
    log.info("Enriching {}", workflowRequest);
    return workflowRequest;
  }
}
