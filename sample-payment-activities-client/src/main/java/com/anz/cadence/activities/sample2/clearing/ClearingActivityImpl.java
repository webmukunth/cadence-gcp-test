package com.anz.cadence.activities.sample2.clearing;

import com.anz.cadence.commons.api.EventType;
import com.anz.cadence.commons.api.PaymentEvent;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.anz.cadence.commons.data.PaymentRequest;
import com.anz.cadence.commons.data.PaymentRequestService;
import com.anz.cadence.commons.kafka.KafkaProducer;
import com.anz.cadence.commons.utils.TraceUtil;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClearingActivityImpl implements ClearingActivity {

  final private PaymentRequestService paymentRequestService;
  final private KafkaProducer kafkaProducer;
  final private TraceUtil traceUtil;

  public ClearingActivityImpl(PaymentRequestService paymentRequestService,
      KafkaProducer kafkaProducer, TraceUtil traceUtil) {
    this.paymentRequestService = paymentRequestService;
    this.kafkaProducer = kafkaProducer;
    this.traceUtil = traceUtil;
  }

  @Override
  public @NonNull ClearingResponse clearPayment(@NonNull WorkflowRequest request) {
    return traceUtil.traceAndMeasureActivity(() -> {
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
    });
  }
}
