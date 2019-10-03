package com.anz.cadence.web.samplepayment.service;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.context.annotation.FilterType.REGEX;

import com.anz.cadence.commons.api.EventType;
import com.anz.cadence.commons.api.PaymentEvent;
import com.anz.cadence.commons.api.workflow.LimitType;
import com.anz.cadence.commons.data.PaymentRequest;
import com.anz.cadence.commons.data.PaymentRequestService;
import com.anz.cadence.commons.kafka.KafkaProducer;
import com.anz.cadence.commons.model.payment.ComAnzFromAcctType;
import com.anz.cadence.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.cadence.commons.model.payment.ComAnzReqMsgHdrType;
import com.anz.cadence.commons.utils.TraceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.testing.TestWorkflowEnvironment;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import reactor.core.Disposable;

@Slf4j
@SpringJUnitConfig(classes = {SubmitPaymentWorkflowClientTest.Config.class})
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.anz",
    basePackageClasses = {JacksonAutoConfiguration.class},
    excludeFilters = {
        @Filter(type = REGEX, pattern = "com.anz.cadence.commons.autoconfigure.CadenceAutoConfiguration"),
        @Filter(type = REGEX, pattern = "com.anz.cadence.commons.autoconfigure.JacksonXMLAutoConfiguration"),
        @Filter(type = REGEX, pattern = "com.anz.cadence.commons.autoconfigure.KafkaAutoConfiguration"),
        @Filter(type = REGEX, pattern = "com.anz.cadence.commons.autoconfigure.ObservabilityAutoConfiguration")
    })
@DataMongoTest
class SubmitPaymentWorkflowClientTest {

  @Autowired
  private KafkaProducer kafkaProducer;
  @Autowired
  private SubmitPaymentWorkflowClient submitPaymentWorkflowClient;
  @Autowired
  private PaymentRequestService paymentRequestService;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void submitPayment() {
    when(kafkaProducer.send(any())).then(invocation -> {
          final var ev = invocation.getArgument(0, PaymentEvent.class);
          log.info("About to send event to kakfka {}", ev);
          assertEquals(ev.getEventType(), EventType.RECEIVED);
          return mock(Disposable.class);
        }
    );
    log.debug("client {}", submitPaymentWorkflowClient);

    final var id = UUID.randomUUID().toString();
    final var gpaRequest = ComAnzPmtAddRqType.builder()
        .rqUID(id)
        .msgHdr(ComAnzReqMsgHdrType.builder().clientName("junit").build())
        .fromAcct(ComAnzFromAcctType.builder().pmtAuthMethod(LimitType.AFPONLY.toString()).build())
        .build();
    final var workflowExecution = submitPaymentWorkflowClient.submitPayment(gpaRequest);
    log.debug("workflowExecution: {}", workflowExecution);
    final var paymentRequest = paymentRequestService.findById(workflowExecution.getWorkflowId());
    log.debug("paymentRequest: {}", paymentRequest);
    assertEquals(id, paymentRequest.getPmtAddRqType().getRqUID());
    assertEquals("junit", paymentRequest.getPmtAddRqType().getMsgHdr().getClientName());
  }

  @Test
  void testRedisSerializer() {
    Jackson2JsonRedisSerializer<PaymentRequest> serializer =
        new Jackson2JsonRedisSerializer(PaymentRequest.class);
    serializer.setObjectMapper(objectMapper);

    final var id = UUID.randomUUID().toString();
    final var gpaRequest = ComAnzPmtAddRqType.builder()
        .rqUID(id)
        .msgHdr(ComAnzReqMsgHdrType.builder().clientName("junit").build())
        .fromAcct(ComAnzFromAcctType.builder().pmtAuthMethod(LimitType.AFPONLY.toString()).build())
        .build();
    final var paymentRequest = PaymentRequest.builder().id(id).pmtAddRqType(gpaRequest).build();
    log.debug("PaymentRequest: {}", paymentRequest);

    byte[] out = serializer.serialize(paymentRequest);
    log.debug("serialize: {}", new String(out, UTF_8));

    final var deserialized = serializer.deserialize(out);
    log.debug("deserialize: {}", deserialized);
  }

  @Configuration
  public static class Config {

    @Bean
    TraceUtil traceUtil() {
      return TraceUtil.getGlobalTraceUtil();
    }

    @Bean
    TestWorkflowEnvironment testEnv() {
      return TestWorkflowEnvironment.newInstance();
    }

    @Bean
    WorkflowClient workflowClient(TestWorkflowEnvironment testEnv) {
      return testEnv.newWorkflowClient();
    }

    @Bean
    KafkaProducer kafkaProducer() {
      return mock(KafkaProducer.class);
    }
  }
}