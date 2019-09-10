package com.anz.magneto.commons.kafka;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.PaymentEvent;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.util.Loggers;

@Slf4j
public class KafkaProducer {

  final private KafkaSender<String, PaymentEvent> kafkaSender;

  public KafkaProducer(KafkaSender<String, PaymentEvent> kafkaSender) {
    this.kafkaSender = kafkaSender;
    Loggers.useSl4jLoggers();
  }

  public Disposable send(PaymentEvent event) {
    SenderRecord<String, PaymentEvent, String> senderRecord =
        SenderRecord.create(Constants.PAYMENT_TOPIC, null,
            System.currentTimeMillis(), event.getClient(), event, event.getId());
    var ret = kafkaSender.send(Mono.just(senderRecord))
        .subscribe(
            r -> {
              log.debug("correlationMetadata: {}", r.correlationMetadata());
              log.debug("offset: {}", r.recordMetadata().offset());
              log.debug("topic: {}", r.recordMetadata().topic());
              log.debug("partition: {}", r.recordMetadata().partition());
              log.debug("timestamp: {}", r.recordMetadata().timestamp());
            },
            e -> log.error("Error occurred", e)
        );
    log.debug("SenderRecord: {}, Result: {}", senderRecord, ret);
    return ret;
  }
}
