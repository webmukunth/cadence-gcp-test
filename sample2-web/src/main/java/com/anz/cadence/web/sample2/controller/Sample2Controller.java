package com.anz.cadence.web.samplepayment.controller;

import static com.anz.cadence.commons.Constants.APPLICATION_VND_GPA_V1_JSON_VALUE;
import static com.anz.cadence.commons.Constants.APPLICATION_VND_GPA_V1_XML_VALUE;
import static com.anz.cadence.commons.Constants.APPLICATION_VND_WF_RES_V1_JSON;
import static com.anz.cadence.commons.Constants.APPLICATION_VND_WF_RES_V_1_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import com.anz.cadence.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.cadence.commons.utils.TraceUtil;
import com.anz.cadence.web.samplepayment.service.Sample2WorkflowClient;
import com.uber.cadence.WorkflowExecution;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/sample2")
public class Sample2Controller {

  final private Sample2WorkflowClient wfClient;
  final private TraceUtil traceUtil;

  @Autowired
  public Sample2Controller(Sample2WorkflowClient wfClient, TraceUtil traceUtil) {
    this.wfClient = wfClient;
    this.traceUtil = traceUtil;
  }

  @PostMapping(
      path = "/executeInAnyOrder",
      consumes = {APPLICATION_VND_GPA_V1_JSON_VALUE, APPLICATION_VND_GPA_V1_XML_VALUE},
      produces = APPLICATION_VND_WF_RES_V_1_JSON_VALUE
  )
  @Timed(
      value = "http_submit_payment_requests",
      description = "Submit Payment",
      histogram = true,
      percentiles = {0.8, 0.9, 0.95, 0.99},
      extraTags = {"consumes", APPLICATION_VND_GPA_V1_JSON_VALUE}
  )
  public ResponseEntity<WorkflowExecution> executeInAnyOrder(
      @RequestBody ComAnzPmtAddRqType request) {
    final var response = wfClient.executeInAnyOrder(request);
    /* Capture workflow information into tracing tags */
    traceUtil.addWorkflowExecutionTag(response);
    log.info("executeInAnyOrder: response={}", response);
    return ok()
        .contentType(APPLICATION_VND_WF_RES_V1_JSON)
        .body(response);
  }

  @PostMapping(
      path = "/executeInOrder",
      consumes = {APPLICATION_VND_GPA_V1_JSON_VALUE, APPLICATION_VND_GPA_V1_XML_VALUE},
      produces = APPLICATION_VND_WF_RES_V_1_JSON_VALUE
  )
  @Timed(
      value = "http_submit_payment_requests",
      description = "Submit Payment",
      histogram = true,
      percentiles = {0.8, 0.9, 0.95, 0.99},
      extraTags = {"consumes", APPLICATION_VND_GPA_V1_JSON_VALUE}
  )
  public ResponseEntity<WorkflowExecution> executeInOrder(@RequestBody ComAnzPmtAddRqType request) {
    final var response = wfClient.executeInOrder(request);
    /* Capture workflow information into tracing tags */
    traceUtil.addWorkflowExecutionTag(response);
    log.info("executeInAnyOrder: response={}", response);
    return ok()
        .contentType(APPLICATION_VND_WF_RES_V1_JSON)
        .body(response);
  }
}
