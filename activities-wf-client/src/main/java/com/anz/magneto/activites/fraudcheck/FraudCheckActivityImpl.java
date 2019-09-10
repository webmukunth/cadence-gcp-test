package com.anz.magneto.activites.fraudcheck;

import com.anz.magneto.commons.api.WorkflowRequest;
import com.anz.magneto.commons.api.WorkflowResponse;
import com.uber.cadence.activity.Activity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FraudCheckActivityImpl implements FraudCheckActivity {

  @Override
  public WorkflowResponse fraudCheck(WorkflowRequest workflowRequest) {
    log.info("fraudCheck: {}", workflowRequest);
    Activity.doNotCompleteOnReturn();
    return null;
  }
}
