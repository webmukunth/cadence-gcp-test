package com.anz.cadence.web.samplepayment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.context.annotation.FilterType.REGEX;

import com.anz.cadence.commons.api.EventType;
import com.anz.cadence.commons.api.PaymentEvent;
import com.anz.cadence.commons.api.workflow.LimitType;
import com.anz.cadence.commons.data.PaymentRequestService;
import com.anz.cadence.commons.kafka.KafkaProducer;
import com.anz.cadence.commons.model.payment.ComAnzFromAcctType;
import com.anz.cadence.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.cadence.commons.model.payment.ComAnzReqMsgHdrType;
import com.anz.cadence.commons.utils.TraceUtil;
import com.anz.cadence.web.samplepayment.service.SubmitPaymentWorkflowClientTest.Config;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.testing.TestWorkflowEnvironment;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import reactor.core.Disposable;

@DataMongoTest
@Slf4j
@SpringJUnitConfig(classes = {Config.class})
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.anz", excludeFilters = {
    @Filter(type = REGEX, pattern = "com.anz.cadence.commons.autoconfigure.CadenceAutoConfiguration"),
    @Filter(type = REGEX, pattern = "com.anz.cadence.commons.autoconfigure.ObservabilityAutoConfiguration")
})
class SubmitPaymentWorkflowClientTest {

  @Autowired
  private KafkaProducer kafkaProducer;
  @Autowired
  private SubmitPaymentWorkflowClient submitPaymentWorkflowClient;
  @Autowired
  private PaymentRequestService paymentRequestService;

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