package com.anz.magneto.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRequestRepository extends MongoRepository<PaymentRequest, String> {

}