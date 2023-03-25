package com.anz.temporal.web.samplepayment.service;

import com.anz.temporal.commons.api.EventType;
import com.anz.temporal.commons.api.PaymentEvent;
import com.anz.temporal.commons.api.workflow.LimitType;
import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import com.anz.temporal.commons.api.workflow.WorkflowResponse;
import com.anz.temporal.commons.data.PaymentRequest;
import com.anz.temporal.commons.data.PaymentRequestService;
import com.anz.temporal.commons.kafka.KafkaProducer;
import com.anz.temporal.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.temporal.commons.utils.TraceUtil;
import com.anz.temporal.workflow.samplepayment.SamplePaymentWorkflow;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.anz.temporal.commons.Constants.TASK_QUEUE;

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
    final var id = Optional.ofNullable(request.getId()).orElse(UUID.randomUUID().toString());

    final var workflowRequest = WorkflowRequest.builder()
        .id(id)
        .rqUID(request.getRqUID())
        .clientName(request.getMsgHdr().getClientName())
        .limitType(LimitType.valueOf(request.getFromAcct().getPmtAuthMethod()))
        .build();

    /* Save to mongodb & redis cache */
    var pr = paymentRequestService.save(
        PaymentRequest.builder().id(id).pmtAddRqType(request).build());
    log.info("Saved paymentRequest {}", pr);

    /* publish event to kafka */
    kafkaProducer.send(PaymentEvent.newInstance(EventType.RECEIVED, workflowRequest));

    log.info("About to submitPayment {}", workflowRequest);

    SamplePaymentWorkflow wfInstance = wfClient.newWorkflowStub(SamplePaymentWorkflow.class,
         WorkflowOptions.newBuilder()
            .setWorkflowId(id)
            .setTaskQueue(TASK_QUEUE)
            .setWorkflowExecutionTimeout(Duration.ofDays(4))
            .build()
    );
    /* Get it back from redis cache */
    return WorkflowClient.start(wfInstance::submitPayment, workflowRequest);
  }

  public WorkflowResponse getResponse(WorkflowExecution workflowExecution) {
    /* Get the instance */
    SamplePaymentWorkflow wfInstance = wfClient.newWorkflowStub(SamplePaymentWorkflow.class,
        workflowExecution.getWorkflowId());
    /* Request is null, since we are getting response from previously execute workflow instance */
    return wfInstance.submitPayment(null);
  }
}
