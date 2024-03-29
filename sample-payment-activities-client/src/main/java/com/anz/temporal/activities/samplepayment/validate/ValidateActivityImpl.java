package com.anz.temporal.activities.samplepayment.validate;

import com.anz.temporal.commons.api.EventType;
import com.anz.temporal.commons.api.PaymentEvent;
import com.anz.temporal.commons.api.workflow.ValidationError;
import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import com.anz.temporal.commons.data.PaymentRequest;
import com.anz.temporal.commons.data.PaymentRequestService;
import com.anz.temporal.commons.kafka.KafkaProducer;
import com.anz.temporal.commons.utils.TraceUtil;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ValidateActivityImpl implements ValidateActivity {

  final private PaymentRequestService paymentRequestService;
  final private KafkaProducer kafkaProducer;
  final private TraceUtil traceUtil;

  public ValidateActivityImpl(PaymentRequestService paymentRequestService,
      KafkaProducer kafkaProducer, TraceUtil traceUtil) {
    this.paymentRequestService = paymentRequestService;
    this.kafkaProducer = kafkaProducer;
    this.traceUtil = traceUtil;
  }

  @Override
  public Optional<List<ValidationError>> validate(WorkflowRequest request) {
    return traceUtil.traceAndMeasureActivity(() -> {
      log.info("validate: {}", request);

      /* Get the payment request from redis/mongo, publish event to kafka */
      PaymentRequest paymentRequest = paymentRequestService.findById(request.getId());
      log.debug("paymentRequest: {}", paymentRequest);
      kafkaProducer.send(PaymentEvent.newInstance(EventType.VALIDATED, request));

    /*return ValidationErrors.builder()
        .error(new ValidationError("c1", "Testing 1"))
        .error(new ValidationError("c2", "Testing 2"))
        .build()
        .getErrors(); */
      return Optional.empty();
    });
  }
}
