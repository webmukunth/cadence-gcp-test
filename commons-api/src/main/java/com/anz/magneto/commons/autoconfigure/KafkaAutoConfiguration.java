package com.anz.magneto.commons.autoconfigure;

import com.anz.magneto.commons.api.PaymentEvent;
import com.anz.magneto.commons.kafka.KafkaProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
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
  public KafkaProducer paymentEventProducer(KafkaProperties kafkaProperties, ObjectMapper mapper) {
    final var prop = kafkaProperties.buildProducerProperties();
    // Trigger the send for every second or batch fills (configured in applicaiton.yaml)
    prop.put(ProducerConfig.LINGER_MS_CONFIG, "1000");
    log.debug("producer properties: {}", prop);

    final SenderOptions<String, PaymentEvent> so =
        SenderOptions.<String, PaymentEvent>create(prop)
            .withKeySerializer(new StringSerializer())
            .withValueSerializer(new JsonSerializer<>(mapper))
            .maxInFlight(32);

    final KafkaProducer ret = new KafkaProducer(KafkaSender.create(so));
    log.info("New paymentEventProducer instance created {}", ret);
    return ret;
  }
}
