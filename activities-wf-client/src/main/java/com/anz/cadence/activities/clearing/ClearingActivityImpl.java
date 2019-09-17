package com.anz.cadence.activities.clearing;

import com.anz.cadence.commons.api.EventType;
import com.anz.cadence.commons.api.PaymentEvent;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.anz.cadence.commons.data.PaymentRequest;
import com.anz.cadence.commons.data.PaymentRequestService;
import com.anz.cadence.commons.kafka.KafkaProducer;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClearingActivityImpl implements ClearingActivity {

  final private PaymentRequestService paymentRequestService;
  final private KafkaProducer kafkaProducer;

  public ClearingActivityImpl(PaymentRequestService paymentRequestService,
      KafkaProducer kafkaProducer) {
    this.paymentRequestService = paymentRequestService;
    this.kafkaProducer = kafkaProducer;
  }

  @Override
  public @NonNull ClearingResponse clearPayment(@NonNull WorkflowRequest request) {
    log.info("clearPayment: request={}", request);

    /* Get the payment request from redis/mongo, publish event to kafka */
    PaymentRequest paymentRequest = paymentRequestService.findById(request.getId());
    log.debug("paymentRequest: {}", paymentRequest);
    kafkaProducer.send(PaymentEvent.newInstance(EventType.PAYMENT_CLEARED, request));

    return ClearingResponse.builder()
        .clearingId(UUID.randomUUID().toString())
        .paymentId(request.getId())
        .rqUID(request.getRqUID())
        .status(ClearingStatus.CLEARED)
        .build();
  }
}
