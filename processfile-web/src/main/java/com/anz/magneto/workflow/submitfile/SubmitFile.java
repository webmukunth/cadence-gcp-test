package com.anz.magneto.workflow.submitfile;

import com.anz.magneto.api.download.FileProcessingWorkflow;
import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.EventType;
import com.anz.magneto.commons.api.PaymentEvent;
import com.anz.magneto.commons.data.PaymentRequest;
import com.anz.magneto.commons.data.PaymentRequestService;
import com.anz.magneto.commons.kafka.KafkaProducer;
import com.anz.magneto.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.magneto.commons.utils.TraceUtil;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import io.micrometer.core.annotation.Timed;
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

  final private WorkflowClient wfClient;
  final private TraceUtil traceUtil;
  final private PaymentRequestService paymentRequestService;
  final private KafkaProducer kafkaProducer;

  @Autowired
  public SubmitFile(WorkflowClient wfClient, TraceUtil traceUtil,
      PaymentRequestService paymentRequestService, KafkaProducer kafkaProducer) {
    this.wfClient = wfClient;
    this.traceUtil = traceUtil;
    this.paymentRequestService = paymentRequestService;
    this.kafkaProducer = kafkaProducer;
    log.info("New instance created");
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

    var ret = WorkflowClient.start(wfInstance::processFile, fileName);
    traceUtil.addWorkflowExecutionTag(ret);
    log.info("Submitted file for proessing: {}", ret);
    return fileName + ":" + ret.toString() + "\n";
  }

  @PostMapping(
      path = "/transform",
      consumes = {"application/vnd.gpa.v1+xml", "application/vnd.gpa.v1+json"},
      produces = "application/vnd.gpa.v1+json"
  )
  @Timed(description = "Transform request V2")
  public ComAnzPmtAddRqType transformV2(@RequestBody ComAnzPmtAddRqType request) {
    String id = UUID.randomUUID().toString();

    /* Save to mongodb & redis cache */
    var pr = paymentRequestService.save(new PaymentRequest(id, request));
    log.info("Saved {}", pr);

    /* publish event to kafka */
    var ev = PaymentEvent.builder()
        .eventType(EventType.RECEIVED)
        .client("test")
        .id(id)
        .build();
    kafkaProducer.send(ev);

    /* Get it back from redis cache */
    return paymentRequestService.findById(id).getPmtAddRqType();
  }
}
