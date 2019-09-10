package com.anz.magneto.commons.autoconfigure;

import com.anz.magneto.commons.api.PaymentEvent;
import com.anz.magneto.commons.kafka.KafkaProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerializer;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
@ConditionalOnClass(value = {KafkaSender.class, ObjectMapper.class, JsonSerializer.class})
@Slf4j
public class KafkaAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public KafkaProducer paymentEventProducer(KafkaProperties kafkaProperties,
      ObjectMapper mapper) {
    var prop = kafkaProperties.buildProducerProperties();
    log.debug("producer properties: {}", prop);

    SenderOptions<String, PaymentEvent> so = SenderOptions.create(prop);
    so.withKeySerializer(new StringSerializer());
    so.withValueSerializer(new JsonSerializer<>(mapper));

    var ret = new KafkaProducer(KafkaSender.create(so));
    log.info("New paymentEventProducer instance created {}", ret);
    return ret;
  }
}
