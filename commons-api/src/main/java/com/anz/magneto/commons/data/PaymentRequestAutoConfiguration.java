package com.anz.magneto.commons.data;

import com.anz.magneto.commons.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConditionalOnClass(value = {MongoRepository.class, RedisCache.class, ObjectMapper.class})
@Slf4j
public class PaymentRequestAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public PaymentRequestService paymentRequestService(PaymentRequestRepository repository) {
    final var ret = new PaymentRequestService(repository);
    log.info("new instance created {}", ret);
    return ret;
  }

  @Bean
  public PaymentRequestCacheCustomizer paymentRequestCacheCustomer(ObjectMapper mapper) {
    final var ret = new PaymentRequestCacheCustomizer(mapper);
    log.debug("Cache customizer instance created {}", ret);
    return ret;
  }

  /*
   * Configure redis cache to use Jackson json serializer instead of default JDK serializer using
   * build customizer, which is invoked by spring boot redis auto configuration
   */
  static class PaymentRequestCacheCustomizer implements RedisCacheManagerBuilderCustomizer {

    final private ObjectMapper mapper;

    PaymentRequestCacheCustomizer(ObjectMapper mapper) {
      this.mapper = mapper;
    }

    @Override
    public void customize(RedisCacheManagerBuilder builder) {
      log.debug("About to customize cache config {} {}", Constants.CACHE_NAME,
          builder.getCacheConfigurationFor(Constants.CACHE_NAME));

      final var serializer = new Jackson2JsonRedisSerializer<>(PaymentRequest.class);
      serializer.setObjectMapper(mapper);

      final var cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
          .entryTtl(Duration.ofMinutes(10))
          .disableCachingNullValues()
          .prefixKeysWith(PaymentRequest.class.getSimpleName() + ":")
          .serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
          .serializeValuesWith(SerializationPair.fromSerializer(serializer));

      builder.withCacheConfiguration(Constants.CACHE_NAME, cacheConfig);
    }
  }
}
