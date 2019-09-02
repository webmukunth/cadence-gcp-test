package com.anz.magneto.commons.kafka;

import com.anz.magneto.commons.Constants;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Slf4j
public class KafkaProducer {

  final private KafkaSender<String,PaymentEvent> kafkaSender;

  public KafkaProducer(KafkaSender<String, PaymentEvent> kafkaSender) {
    this.kafkaSender = kafkaSender;
  }

  public void send(PaymentEvent event) {
    SenderRecord<String,PaymentEvent,String> senderRecord =
        SenderRecord.create(Constants.PAYMENT_TOPIC, null,
            System.currentTimeMillis(), event.getClient(), event, event.getId() );

    final var ret = kafkaSender.send( Mono.just(senderRecord)).next();

    log.debug( "SenderRecord: {}, Result: {}", senderRecord, ret);
  }
}
