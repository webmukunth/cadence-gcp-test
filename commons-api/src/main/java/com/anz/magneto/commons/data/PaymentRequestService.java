package com.anz.magneto.commons.data;

import com.anz.magneto.commons.Constants;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentRequestService {

  final private PaymentRequestRepository repository;

  public PaymentRequestService(PaymentRequestRepository repository) {
    this.repository = repository;
  }

  /* Store in Mongo and Redis cache */
  @CachePut(cacheNames = Constants.CACHE_NAME, key = "#request.getId()")
  public PaymentRequest save(PaymentRequest request) {
    log.debug("Save {}", request.getId());
    return repository.save(request);
  }

  /* Get from redis cache backed by mongodb */
  @Cacheable(cacheNames = Constants.CACHE_NAME)
  public PaymentRequest findById(String id) {
    log.debug("findById {}", id);
    return repository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Unable to find payment request id " + id));
  }
}