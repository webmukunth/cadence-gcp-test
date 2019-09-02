package com.anz.magneto.service.download;

import com.anz.magneto.api.download.DownloadActivity;
import com.anz.magneto.api.download.ProcessFileActivity;
import com.anz.magneto.commons.autoconfigure.CadenceAutoConfiguration;
import com.anz.magneto.commons.Constants;
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
public class WorkerAutoConfiguration {

  @Value("${spring.application.name:no-name}")
  private String applicationName;

  @Bean
  @ConditionalOnMissingBean
  public Factory workerFactory(WorkflowServiceTChannel tc, Scope ms,
      DownloadActivity downloadAct, ProcessFileActivity processFileAct) {

    log.debug("   downloadActivity: {}", downloadAct);
    log.debug("processFileActivity: {}", processFileAct);

    /* Create Factory */
    final var fo = new FactoryOptions.Builder()
        .setMetricScope(ms)
        .setDisableStickyExecution(true)
        .setMaxWorkflowThreadCount(4)
        .build();
    final var f = new Factory(tc, Constants.DOMAIN, fo);
    log.info("Initialized workerFactory {}", f);

    /* Create Main Worker */

    final var worker = f.newWorker(Constants.TASK_LIST,
        new WorkerOptions.Builder()
            .setWorkflowPollerOptions(
                new PollerOptions.Builder()
                    .setPollThreadCount(1)
                    .setMaximumPollRateIntervalMilliseconds(1000)
                    .setPollThreadNamePrefix("Cadence Workflow Poller")
                    .build())
            .setActivityPollerOptions(
                new PollerOptions.Builder()
                    .setPollThreadCount(1)
                    .setMaximumPollRateIntervalMilliseconds(1000)
                    .setPollThreadNamePrefix("Cadence Activity Poller")
                    .build())
            .setMaxConcurrentActivityExecutionSize(16)
            .setMaxConcurrentWorkflowExecutionSize(16)
            .setIdentity(applicationName + "@" + Constants.INSTANCE_NAME)
            .setMetricsScope(ms)
            .build()
    );

    log.info("Initialized worker {}", worker);

    /* Register Workflows with main worker */
    worker.registerWorkflowImplementationTypes(
        new WorkflowImplementationOptions.Builder().build(),
        FileProcessingWorkflowImpl.class);

    /* Register Download Activity  with worker */
    worker.registerActivitiesImplementations(downloadAct, processFileAct);

    /* Start the worker */
    f.start();

    log.info("Worker factory started");
    return f;
  }
}
