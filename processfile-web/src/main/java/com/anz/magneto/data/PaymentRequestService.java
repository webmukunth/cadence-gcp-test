package com.anz.magneto.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentRequestService {

  final private static String CACHE_NAME = "paymentRequest";
  final private PaymentRequestRepository repository;

  public PaymentRequestService(PaymentRequestRepository repository) {
    this.repository = repository;
  }

  /* Store in Mongo and Redis cache */
  @CachePut(cacheNames = CACHE_NAME, key = "#request.getId()")
  public PaymentRequest save(PaymentRequest request) {
    log.debug("Save {}", request.getId());
    return repository.save(request);
  }

  @Cacheable(cacheNames = CACHE_NAME)
  public PaymentRequest findById(String id) {
    log.debug("findById {}", id);
    return repository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Unable to find payment request id " + id));
  }


  /*
   * Configure redis cache to use Jackson json serializer instead of default JDK serializer using
   * build customizer, which is invoked by spring boot redis auto configuration
   */
  @Slf4j
  static class PaymentRequestCacheCustomizer implements RedisCacheManagerBuilderCustomizer {

    final private ObjectMapper mapper;

    PaymentRequestCacheCustomizer(ObjectMapper mapper) {
      this.mapper = mapper;
    }

    @Override
    public void customize(RedisCacheManagerBuilder builder) {
      log.debug("About to customize cache config {} {}", CACHE_NAME,
          builder.getCacheConfigurationFor(CACHE_NAME));

      final var serializer = new Jackson2JsonRedisSerializer<>(PaymentRequest.class);
      serializer.setObjectMapper(mapper);

      final var cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
          .entryTtl(Duration.ofMinutes(10))
          .disableCachingNullValues()
          .prefixKeysWith(PaymentRequest.class.getSimpleName() + ":")
          .serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
          .serializeValuesWith(SerializationPair.fromSerializer(serializer));

      builder.withCacheConfiguration(CACHE_NAME, cacheConfig);
    }
  }

  /*
   * Create customizer bean for redis auto configuration
   */
  @Configuration
  @Slf4j
  @ConditionalOnClass(ObjectMapper.class)
  static class PaymentRequestCacheConfiguration {

    @Bean
    PaymentRequestCacheCustomizer paymentRequestCacheCustomer(ObjectMapper mapper) {
      final var ret = new PaymentRequestCacheCustomizer(mapper);
      log.debug("Cache customizer instance created {}", ret);
      return ret;
    }
  }
}