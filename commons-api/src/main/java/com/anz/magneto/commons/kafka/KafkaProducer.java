package com.anz.magneto.commons.kafka;

import com.anz.magneto.commons.Constants;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;
import reactor.util.Loggers;

@Slf4j
public class KafkaProducer {

  final private KafkaSender<String,PaymentEvent> kafkaSender;

  public KafkaProducer(KafkaSender<String, PaymentEvent> kafkaSender) {
    this.kafkaSender = kafkaSender;
    Loggers.useSl4jLoggers();
  }

  public Mono<SenderResult<String>> send(PaymentEvent event) {
    SenderRecord<String,PaymentEvent,String> senderRecord =
        SenderRecord.create(Constants.PAYMENT_TOPIC, null,
            System.currentTimeMillis(), event.getClient(), event, event.getId() );
    final var ret = kafkaSender.send( Mono.just(senderRecord))
        .next()
        .log("reactor.paymentevent")
        .doOnError(e -> log.error("Send failed for " + event, e));
    log.debug( "SenderRecord: {}, Result: {}", senderRecord, ret);
    return ret;
  }
}
