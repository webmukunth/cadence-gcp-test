package com.anz.magneto.activities.accounting;

import static com.anz.magneto.activities.accounting.AccountingStatus.SUCCESS;

import com.anz.magneto.commons.api.EventType;
import com.anz.magneto.commons.api.PaymentEvent;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.anz.magneto.commons.data.PaymentRequest;
import com.anz.magneto.commons.data.PaymentRequestService;
import com.anz.magneto.commons.kafka.KafkaProducer;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountingActivityImpl implements AccountingActivity {

  final private PaymentRequestService paymentRequestService;
  final private KafkaProducer kafkaProducer;

  @Autowired
  public AccountingActivityImpl(PaymentRequestService paymentRequestService,
      KafkaProducer kafkaProducer) {
    this.paymentRequestService = paymentRequestService;
    this.kafkaProducer = kafkaProducer;
  }

  @Override
  public @NonNull AccountingResponse reverseDebitCustomerCreditFloat(
      @NonNull WorkflowRequest request, @NonNull AccountingResponse originalResponse) {
    log.info("reverseDebitCustomerCreditFloat: request={}, originalResponse: {}", request,
        originalResponse);

    /* Unique id generated based on string, this can prevent accidental duplicate request to DS */
    var reverseAccountingId = UUID.nameUUIDFromBytes(
        (originalResponse.getAccountingId() + "-reverse").getBytes(StandardCharsets.UTF_8)
    ).toString();

    /* Get the payment request from redis/mongo, publish event to kafka */
    PaymentRequest paymentRequest = paymentRequestService.findById(request.getId());
    log.debug("paymentRequest: {}", paymentRequest);
    kafkaProducer.send(PaymentEvent.newInstance(EventType.CUSTOMER_DEBIT_REVERSED, request));

    return AccountingResponse.builder()
        .status(SUCCESS)
        .paymentId(request.getId())
        .rqUID(request.getRqUID())
        .accountingId(reverseAccountingId)
        .build();
  }

  @Override
  public @NonNull AccountingResponse debitCustomerCreditFloat(@NonNull WorkflowRequest request) {
    log.info("debitCustomerCreditFloat: request={}", request);

    /* Unique id generated based on string, this can prevent accidental duplicate request to DS */
    var accountingId = UUID.nameUUIDFromBytes(
        (request.getId() + "-debitCustomerCreditFloat").getBytes(StandardCharsets.UTF_8)
    ).toString();

    /* Get the payment request from redis/mongo, publish event to kafka */
    PaymentRequest paymentRequest = paymentRequestService.findById(request.getId());
    log.debug("paymentRequest: {}", paymentRequest);
    kafkaProducer.send(PaymentEvent.newInstance(EventType.CUSTOMER_DEBITTED, request));

    return AccountingResponse.builder()
        .status(SUCCESS)
        .paymentId(request.getId())
        .rqUID(request.getRqUID())
        .accountingId(accountingId)
        .build();
  }

  @Override
  public @NonNull AccountingResponse forceDebitCustomerCreditFloat(
      @NonNull WorkflowRequest request) {
    log.info("forceDebitCustomerCreditFloat: request={}", request);

    /* Unique id generated based on string, this can prevent accidental duplicate request to DS */
    var accountingId = UUID.nameUUIDFromBytes(
        (request.getId() + "-forceDebitCustomerCreditFloat").getBytes(StandardCharsets.UTF_8)
    ).toString();

    /* Get the payment request from redis/mongo, publish event to kafka */
    PaymentRequest paymentRequest = paymentRequestService.findById(request.getId());
    log.debug("paymentRequest: {}", paymentRequest);
    kafkaProducer.send(PaymentEvent.newInstance(EventType.CUSTOMER_FORCE_DEBITTED, request));

    return AccountingResponse.builder()
        .status(SUCCESS)
        .paymentId(request.getId())
        .rqUID(request.getRqUID())
        .accountingId(accountingId)
        .build();
  }
}
