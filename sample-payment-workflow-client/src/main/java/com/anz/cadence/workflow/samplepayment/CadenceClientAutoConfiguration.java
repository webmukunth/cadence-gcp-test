package com.anz.cadence.workflow.samplepayment;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.autoconfigure.CadenceAutoConfiguration;
import com.uber.cadence.internal.worker.PollerOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker.Factory;
import com.uber.cadence.worker.Worker.FactoryOptions;
import com.uber.cadence.worker.WorkerOptions;
import com.uber.cadence.worker.WorkflowImplementationOptions;
import com.uber.m3.tally.Scope;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(CadenceAutoConfiguration.class)
@Slf4j
public class CadenceClientAutoConfiguration {

  @Value("${spring.application.name:no-name}")
  private String applicationName;

  @Bean
  @ConditionalOnMissingBean
  public Factory workerFactory(WorkflowServiceTChannel tc, Scope ms) {

    /* Create Factory */
    var fo = new FactoryOptions.Builder()
        .setMetricScope(ms)
        .setDisableStickyExecution(true)
        .setMaxWorkflowThreadCount(32)
        .build();

    var f = new Factory(tc, Constants.DOMAIN, fo);
    log.info("Initialized workerFactory {}", f);

    final var wo = new WorkerOptions.Builder()
        .setWorkflowPollerOptions(
            new PollerOptions.Builder()
                .setPollThreadCount(2)
                .setPollBackoffInitialInterval(Duration.ofMillis(100))
                .setPollBackoffMaximumInterval(Duration.ofSeconds(1))
                .setPollThreadNamePrefix("Cadence Workflow Poller")
                .build())
        .setMaxConcurrentWorkflowExecutionSize(1024)
        .setIdentity(applicationName + "@" + Constants.INSTANCE_NAME)
        .setMetricsScope(ms)
        .setDisableActivityWorker(true)  // Activities are not running in this VM
        .build();

    /* Create Main Worker */
    var worker = f.newWorker(Constants.TASK_LIST_SAMPLE_PAYMENT, wo);
    log.info("WorkerOption: {}l", wo);
    log.info("Initialized worker {}", worker);

    /* Register Workflow with main worker */
    worker.registerWorkflowImplementationTypes(
        new WorkflowImplementationOptions.Builder().build(), SamplePaymentWorkflowImpl.class);

    /* Start the worker */
    f.start();
    log.info("Worker factory started");
    return f;
  }
}