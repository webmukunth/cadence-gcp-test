package com.anz.magneto.workflow.submitfile;

import com.anz.magneto.api.download.FileProcessingWorkflow;
import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.data.PaymentRequest;
import com.anz.magneto.commons.data.PaymentRequestService;
import com.anz.magneto.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.magneto.commons.utils.TraceUtil;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import io.micrometer.core.annotation.Timed;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SubmitFile {

  private WorkflowClient wfClient;
  private TraceUtil traceUtil;
  private XmlMapper xmlMapper;
  private PaymentRequestService paymentRequestService;

  @Autowired
  public SubmitFile(WorkflowClient wfClient, TraceUtil traceUtil, XmlMapper xmlMapper,
      PaymentRequestService paymentRequestService) {
    this.wfClient = wfClient;
    this.traceUtil = traceUtil;
    this.xmlMapper = xmlMapper;
    this.paymentRequestService = paymentRequestService;
    log.info("workflowClient: {}", wfClient);
  }

  @RequestMapping("/submitfile/{filename}")
  @Timed(description = "Submit file for processing")
  public String submitFile(@PathVariable("filename") String fileName) {
    traceUtil.addTag("filename", fileName);

    log.info("Got a request to submit file : {}", fileName);

    FileProcessingWorkflow wfInstance = wfClient.newWorkflowStub(
        FileProcessingWorkflow.class,
        new WorkflowOptions.Builder()
            .setTaskList(Constants.TASK_LIST)
            .build()
    );

    final var ret = WorkflowClient.start(wfInstance::processFile, fileName);
    traceUtil.addWorkflowExecutionTag(ret);
    log.info("Submitted file for proessing: {}", ret);
    return fileName + ":" + ret.toString() + "\n";
  }

  @PostMapping(
      path = "/transform",
      consumes = "application/vnd.gpa.v1+xml",
      produces = "application/vnd.gpa.v1+json"
  )
  @Timed(description = "Transform request V1")
  public ComAnzPmtAddRqType transformV1(InputStream inputStream) throws IOException {
    final var ret = xmlMapper.readValue(inputStream, ComAnzPmtAddRqType.class);
    log.info("Request V1: {}", ret);
    return ret;
  }

  @PostMapping(
      path = "/transform",
      consumes = {"application/vnd.gpa.v2+xml", "application/vnd.gpa.v2+json"},
      produces = "application/vnd.gpa.v2+json"
  )
  @Timed(description = "Transform request V2")
  public ComAnzPmtAddRqType transformV2(@RequestBody ComAnzPmtAddRqType request) {
    String id = UUID.randomUUID().toString();
    final var pr = paymentRequestService.save(new PaymentRequest(id, request));
    log.info("Saved {}", pr);
    return paymentRequestService.findById(id).getPmtAddRqType();
  }
}
