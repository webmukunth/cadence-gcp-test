package com.anz.magneto.web.samplepayment;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.EventType;
import com.anz.magneto.commons.api.PaymentEvent;
import com.anz.magneto.commons.api.workflow.LimitType;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.anz.magneto.commons.data.PaymentRequest;
import com.anz.magneto.commons.data.PaymentRequestService;
import com.anz.magneto.commons.kafka.KafkaProducer;
import com.anz.magneto.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.magneto.commons.utils.TraceUtil;
import com.anz.magneto.samplepayment.SamplePaymentWorkflow;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import io.micrometer.core.annotation.Timed;
import java.time.Duration;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SubmitPayment {

  final private WorkflowClient wfClient;
  final private TraceUtil traceUtil;
  final private PaymentRequestService paymentRequestService;
  final private KafkaProducer kafkaProducer;

  @Autowired
  public SubmitPayment(WorkflowClient wfClient, TraceUtil traceUtil,
      PaymentRequestService paymentRequestService, KafkaProducer kafkaProducer) {
    this.wfClient = wfClient;
    this.traceUtil = traceUtil;
    this.paymentRequestService = paymentRequestService;
    this.kafkaProducer = kafkaProducer;
    log.info("New instance created");
  }

  @PostMapping(
      path = "/submitPayment",
      consumes = {"application/vnd.gpa.v1+xml", "application/vnd.gpa.v1+json"},
      produces = "application/vnd.wf-res.v1+json"
  )
  @Timed(description = "Transform request V2")
  public WorkflowRequest submitPayment(@RequestBody ComAnzPmtAddRqType request) {

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

    final var workflowRequest = WorkflowRequest.builder()
        .requestId(id)
        .limitType(LimitType.valueOf(request.getFromAcct().getPmtAuthMethod()))
        .build();

    log.info("About to submitPayment {}", workflowRequest);

    SamplePaymentWorkflow wfInstance = wfClient.newWorkflowStub(SamplePaymentWorkflow.class,
        new WorkflowOptions.Builder()
            .setTaskList(Constants.TASK_LIST)
            .setWorkflowId(id)
            .setExecutionStartToCloseTimeout(Duration.ofDays(4))
            .build()
    );
    WorkflowClient.start(wfInstance::submitPayment, workflowRequest);
    /* Get it back from redis cache */
    return workflowRequest;
  }
}
