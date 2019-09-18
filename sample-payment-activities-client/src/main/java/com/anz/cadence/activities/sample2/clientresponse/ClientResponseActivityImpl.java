package com.anz.cadence.activities.sample2.clientresponse;

import com.anz.cadence.commons.api.EventType;
import com.anz.cadence.commons.api.PaymentEvent;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.anz.cadence.commons.api.workflow.WorkflowResponse;
import com.anz.cadence.commons.data.PaymentRequest;
import com.anz.cadence.commons.data.PaymentRequestService;
import com.anz.cadence.commons.kafka.KafkaProducer;
import com.anz.cadence.commons.utils.TraceUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientResponseActivityImpl implements ClientResponseActivity {

  final private PaymentRequestService paymentRequestService;
  final private KafkaProducer kafkaProducer;
  final private TraceUtil traceUtil;

  public ClientResponseActivityImpl(PaymentRequestService paymentRequestService,
      KafkaProducer kafkaProducer, TraceUtil traceUtil) {
    this.paymentRequestService = paymentRequestService;
    this.kafkaProducer = kafkaProducer;
    this.traceUtil = traceUtil;
  }

  @Override
  public void sendResponse(@NonNull WorkflowRequest request, @NonNull WorkflowResponse response) {
    traceUtil.traceAndMeasureActivity(() -> {
      log.info("sendResponse: request={}, response={}", request, response);

      /* Get the payment request from redis/mongo, publish event to kafka */
      PaymentRequest paymentRequest = paymentRequestService.findById(request.getId());
      log.debug("paymentRequest: {}", paymentRequest);
      kafkaProducer.send(PaymentEvent.newInstance(EventType.CLIENT_RESPONSE, request));
      return null;
    });
  }
}
