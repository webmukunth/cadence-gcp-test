package com.anz.temporal.workflow.samplepayment;

import com.anz.temporal.commons.Constants;
import com.anz.temporal.commons.autoconfigure.TemporalAutoConfiguration;
import com.anz.temporal.commons.autoconfigure.TemporalConfigurationProperties;
import com.anz.temporal.commons.utils.TraceUtil;
import io.temporal.client.WorkflowClient;
import io.temporal.opentracing.OpenTracingWorkerInterceptor;
import io.temporal.worker.WorkerFactory;
import com.uber.m3.tally.Scope;
import java.util.stream.IntStream;

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
  public WorkerFactory workerFactory(WorkflowClient tc, TemporalConfigurationProperties prop,Scope ms) {

    /* Create Factory */
    var wfo = WorkerFactoryOptions.newBuilder()
            .setWorkerInterceptors(
                    new OpenTracingWorkerInterceptor(TraceUtil.getJaegerOpenTracingOptions(prop)))
            .build();

    var wf = WorkerFactory.newInstance(tc, wfo);
    log.info("Initialized workerFactory {}", wf);

    final var wo = WorkerOptions.newBuilder()
            .setMaxConcurrentActivityTaskPollers(8)
            .setMaxTaskQueueActivitiesPerSecond(1000000)
            .setMaxWorkerActivitiesPerSecond(100000)
            .build();

    /* Create Main Worker */
    IntStream.range(0, 1).forEach(i ->
        wf.newWorker(Constants.TASK_QUEUE, wo)
            .registerWorkflowImplementationTypes(SamplePaymentWorkflowImpl.class)
    );

    /* Start the worker */
    wf.start();
    log.info("Worker factory started");
    return wf;
  }
}
