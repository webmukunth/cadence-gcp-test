package com.anz.magneto.activities.limitcheck;

import com.anz.magneto.commons.api.EventType;
import com.anz.magneto.commons.api.PaymentEvent;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.anz.magneto.commons.data.PaymentRequest;
import com.anz.magneto.commons.data.PaymentRequestService;
import com.anz.magneto.commons.kafka.KafkaProducer;
import javax.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LimitCheckActivityImpl implements LimitCheckActivity {

  final private PaymentRequestService paymentRequestService;
  final private KafkaProducer kafkaProducer;

  public LimitCheckActivityImpl(PaymentRequestService paymentRequestService,
      KafkaProducer kafkaProducer) {
    this.paymentRequestService = paymentRequestService;
    this.kafkaProducer = kafkaProducer;
  }

  @Override
  public @NonNull LimitCheckOutcome limitCheck(@NotNull WorkflowRequest request) {
    log.info("limitCheck: {}", request);
    /* Get the payment request from redis/mongo, publish event to kafka */
    PaymentRequest paymentRequest = paymentRequestService.findById(request.getId());
    log.debug("paymentRequest: {}", paymentRequest);
    kafkaProducer.send(PaymentEvent.newInstance(EventType.LIMIT_CHECK_PASS, request));

    return LimitCheckOutcome.PASS;
  }
}
