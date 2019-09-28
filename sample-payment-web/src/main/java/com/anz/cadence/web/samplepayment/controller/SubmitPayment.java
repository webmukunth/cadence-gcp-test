package com.anz.cadence.web.samplepayment.controller;

import static com.anz.cadence.commons.Constants.APPLICATION_VND_GPA_V1_JSON_VALUE;
import static com.anz.cadence.commons.Constants.APPLICATION_VND_GPA_V1_XML_VALUE;
import static com.anz.cadence.commons.Constants.APPLICATION_VND_WF_RES_V1_JSON;
import static com.anz.cadence.commons.Constants.APPLICATION_VND_WF_RES_V_1_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import com.anz.cadence.commons.api.workflow.WorkflowResponse;
import com.anz.cadence.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.cadence.commons.utils.TraceUtil;
import com.anz.cadence.web.samplepayment.service.SubmitPaymentWorkflowClient;
import com.uber.cadence.WorkflowExecution;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SubmitPayment {

  final private SubmitPaymentWorkflowClient wfClient;
  final private TraceUtil traceUtil;

  @Autowired
  public SubmitPayment(SubmitPaymentWorkflowClient wfClient, TraceUtil traceUtil) {
    this.wfClient = wfClient;
    this.traceUtil = traceUtil;
  }

  @PostMapping(
      path = "/submitPayment",
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
  public ResponseEntity<WorkflowExecution> submitPayment(@RequestBody ComAnzPmtAddRqType request) {
    final var response = wfClient.submitPayment(request);
    /* Capture workflow information into tracing tags */
    traceUtil.addWorkflowExecutionTag(response);
    log.info("submitPayment response={}", response);
    return ok()
        .contentType(APPLICATION_VND_WF_RES_V1_JSON)
        .body(response);
  }

  @PostMapping(
      path = "/executePayment",
      consumes = {APPLICATION_VND_GPA_V1_JSON_VALUE, APPLICATION_VND_GPA_V1_XML_VALUE},
      produces = APPLICATION_VND_WF_RES_V_1_JSON_VALUE
  )
  @Timed(
      value = "http_execute_payment_requests",
      description = "Execute Payment",
      histogram = true,
      percentiles = {0.8, 0.9, 0.95, 0.99},
      extraTags = {"consumes", APPLICATION_VND_GPA_V1_JSON_VALUE}
  )
  public ResponseEntity<WorkflowResponse> executePayment(@RequestBody ComAnzPmtAddRqType request) {
    final var workflowExecution = wfClient.submitPayment(request);
    /* Capture workflow information into tracing tags */
    traceUtil.addWorkflowExecutionTag(workflowExecution);
    log.info("executePayment workflowExecution={}", workflowExecution);
    final var response = wfClient.getResponse(workflowExecution);
    log.info("executePayment response={}", response);
    return ok()
        .contentType(APPLICATION_VND_WF_RES_V1_JSON)
        .body(response);
  }
}
