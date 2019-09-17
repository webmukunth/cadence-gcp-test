package com.anz.cadence.web.samplepayment.service;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.EventType;
import com.anz.cadence.commons.api.PaymentEvent;
import com.anz.cadence.commons.api.workflow.LimitType;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.anz.cadence.commons.data.PaymentRequest;
import com.anz.cadence.commons.data.PaymentRequestService;
import com.anz.cadence.commons.kafka.KafkaProducer;
import com.anz.cadence.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.cadence.commons.utils.TraceUtil;
import com.anz.cadence.samplepayment.SamplePaymentWorkflow;
import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import java.time.Duration;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubmitPaymentWorkflowClient {

  final private WorkflowClient wfClient;
  final private TraceUtil traceUtil;
  final private PaymentRequestService paymentRequestService;
  final private KafkaProducer kafkaProducer;

  @Autowired
  public SubmitPaymentWorkflowClient(WorkflowClient wfClient, TraceUtil traceUtil,
      PaymentRequestService paymentRequestService, KafkaProducer kafkaProducer) {
    this.wfClient = wfClient;
    this.traceUtil = traceUtil;
    this.paymentRequestService = paymentRequestService;
    this.kafkaProducer = kafkaProducer;
  }


  public WorkflowExecution submitPayment(ComAnzPmtAddRqType request) {
    String id = UUID.randomUUID().toString();

    final var workflowRequest = WorkflowRequest.builder()
        .id(id)
        .rqUID(request.getRqUID())
        .clientName(request.getMsgHdr().getClientName())
        .limitType(LimitType.valueOf(request.getFromAcct().getPmtAuthMethod()))
        .build();

    /* Save to mongodb & redis cache */
    var pr = paymentRequestService.save(new PaymentRequest(id, request));
    log.info("Saved paymentRequest {}", pr);

    /* publish event to kafka */
    kafkaProducer.send(PaymentEvent.newInstance(EventType.RECEIVED, workflowRequest));

    log.info("About to submitPayment {}", workflowRequest);

    SamplePaymentWorkflow wfInstance = wfClient.newWorkflowStub(SamplePaymentWorkflow.class,
        new WorkflowOptions.Builder()
            .setTaskList(Constants.TASK_LIST)
            .setWorkflowId(id)
            .setExecutionStartToCloseTimeout(Duration.ofDays(4))
            .build()
    );
    /* Get it back from redis cache */
    return WorkflowClient.start(wfInstance::submitPayment, workflowRequest);
  }
}