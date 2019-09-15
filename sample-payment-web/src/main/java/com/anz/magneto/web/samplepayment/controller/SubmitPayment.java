package com.anz.magneto.web.samplepayment.controller;

import static com.anz.magneto.commons.Constants.APPLICATION_VND_GPA_V1_JSON_VALUE;
import static com.anz.magneto.commons.Constants.APPLICATION_VND_GPA_V1_XML_VALUE;
import static com.anz.magneto.commons.Constants.APPLICATION_VND_WF_RES_V1_JSON;
import static com.anz.magneto.commons.Constants.APPLICATION_VND_WF_RES_V_1_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import com.anz.magneto.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.magneto.web.samplepayment.service.SubmitPaymentWorkflowClient;
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

  @Autowired
  public SubmitPayment(SubmitPaymentWorkflowClient wfClient) {
    this.wfClient = wfClient;
  }

  @PostMapping(
      path = "/submitPayment",
      consumes = {APPLICATION_VND_GPA_V1_JSON_VALUE, APPLICATION_VND_GPA_V1_XML_VALUE},
      produces = APPLICATION_VND_WF_RES_V_1_JSON_VALUE
  )
  @Timed(
      description = "Submit Payment",
      histogram = true,
      percentiles = {0.8, 0.9, 0.95, 0.99},
      extraTags = {"consumes", APPLICATION_VND_GPA_V1_JSON_VALUE}
  )
  public ResponseEntity<WorkflowExecution> submitPayment(@RequestBody ComAnzPmtAddRqType request) {
    final var response = wfClient.submitPayment(request);
    log.info("submitPayment response={}", response);
    return ok()
        .contentType(APPLICATION_VND_WF_RES_V1_JSON)
        .body(response);
  }
}
