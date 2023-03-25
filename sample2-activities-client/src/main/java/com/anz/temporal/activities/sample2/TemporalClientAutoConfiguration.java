package com.anz.temporal.activities.sample2;

import com.anz.temporal.commons.Constants;
import com.anz.temporal.commons.autoconfigure.TemporalAutoConfiguration;
import com.anz.temporal.commons.autoconfigure.TemporalConfigurationProperties;
import com.anz.temporal.commons.utils.TraceUtil;
import io.temporal.client.WorkflowClient;
import io.temporal.opentracing.OpenTracingWorkerInterceptor;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.WorkerFactory;
import com.uber.m3.tally.Scope;
import io.temporal.worker.WorkerFactoryOptions;
import io.temporal.worker.WorkerOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(TemporalAutoConfiguration.class)
@Slf4j
public class TemporalClientAutoConfiguration {

  @Value("${spring.application.name:no-name}")
  private String applicationName;

  @Bean
  @ConditionalOnMissingBean
  public WorkerFactory workerFactory(Scope ms,
                               ServiceAActivity serviceAActivity, ServiceBActivity serviceBActivity, TemporalConfigurationProperties prop) {

    final var opt = WorkflowServiceStubsOptions.newBuilder()
            .setMetricsScope(ms).build();

    final WorkflowServiceStubs service = WorkflowServiceStubs.newServiceStubs(opt);
    final WorkflowClient client = WorkflowClient.newInstance(service);

    /* Create Factory */
    var fo = WorkerFactoryOptions.newBuilder()
            .setWorkerInterceptors(
                    new OpenTracingWorkerInterceptor(TraceUtil.getJaegerOpenTracingOptions(prop)))
        .setMaxWorkflowThreadCount(4)
        .build();

    final WorkerFactory factory = WorkerFactory.newInstance(client,fo);

    /* Create Main Worker */

    var worker = factory.newWorker(Constants.TASK_QUEUE,
            WorkerOptions.newBuilder()
                    .setMaxConcurrentActivityTaskPollers(1)
                    .build());

    log.info("Initialized worker {}", worker);

    /* Register Download Activity  with worker */
    worker.registerActivitiesImplementations(serviceAActivity, serviceBActivity);
    /* Start the worker */
    factory.start();
    log.info("Worker factory started");
    return factory;
  }
}
