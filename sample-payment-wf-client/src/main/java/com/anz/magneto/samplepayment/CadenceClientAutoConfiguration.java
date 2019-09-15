package com.anz.magneto.samplepayment;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.autoconfigure.CadenceAutoConfiguration;
import com.uber.cadence.internal.worker.PollerOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker.Factory;
import com.uber.cadence.worker.Worker.FactoryOptions;
import com.uber.cadence.worker.WorkerOptions;
import com.uber.cadence.worker.WorkflowImplementationOptions;
import com.uber.m3.tally.Scope;
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
        .setMaxWorkflowThreadCount(4)
        .build();

    var f = new Factory(tc, Constants.DOMAIN, fo);
    log.info("Initialized workerFactory {}", f);

    /* Create Main Worker */
    var worker = f.newWorker(Constants.TASK_LIST,
        new WorkerOptions.Builder()
            .setWorkflowPollerOptions(
                new PollerOptions.Builder()
                    .setPollThreadCount(1)
                    .setMaximumPollRateIntervalMilliseconds(1000)
                    .setPollThreadNamePrefix("Cadence Workflow Poller")
                    .build())
            .setDisableActivityWorker(true)  // Activities are not running in this VM
            .setMaxConcurrentWorkflowExecutionSize(16)
            .setIdentity(applicationName + "@" + Constants.INSTANCE_NAME)
            .setMetricsScope(ms)
            .build()
    );

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
