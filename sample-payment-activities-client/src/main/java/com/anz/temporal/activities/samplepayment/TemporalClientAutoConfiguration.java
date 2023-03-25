package com.anz.temporal.activities.samplepayment;

import com.anz.temporal.activities.samplepayment.accounting.AccountingActivity;
import com.anz.temporal.activities.samplepayment.clearing.ClearingActivity;
import com.anz.temporal.activities.samplepayment.clientresponse.ClientResponseActivity;
import com.anz.temporal.activities.samplepayment.enrich.EnrichActivity;
import com.anz.temporal.activities.samplepayment.fraudcheck.FraudCheckActivity;
import com.anz.temporal.activities.samplepayment.limitcheck.LimitCheckActivity;
import com.anz.temporal.activities.samplepayment.validate.ValidateActivity;
import com.anz.temporal.commons.Constants;
import com.anz.temporal.commons.autoconfigure.TemporalAutoConfiguration;
import com.anz.temporal.commons.autoconfigure.TemporalConfigurationProperties;
import com.anz.temporal.commons.utils.TraceUtil;
import io.temporal.opentracing.OpenTracingWorkerInterceptor;
import io.temporal.worker.WorkerFactory;
import io.temporal.client.WorkflowClient;
import io.temporal.worker.WorkerOptions;
import io.temporal.worker.WorkerFactoryOptions;

//import com.uber.m3.tally.Scope;

import java.util.stream.IntStream;
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

 /* @Bean
  @ConditionalOnMissingBean
  public WorkerFactory workerFactory(WorkflowClient tc,
                                     AccountingActivity accountingActivity, ClearingActivity clearingActivity,
                                     ClientResponseActivity clientResponseActivity, EnrichActivity enrichActivity,
                                     FraudCheckActivity fraudCheckActivity, LimitCheckActivity limitCheckActivity,
                                     ValidateActivity validateActivity, TemporalConfigurationProperties prop) {

    *//* Create Factory *//*
    var wfo = WorkerFactoryOptions.newBuilder()
            .setWorkerInterceptors(
                    new OpenTracingWorkerInterceptor(TraceUtil.getJaegerOpenTracingOptions(prop)))
        .build();

    var wf = WorkerFactory.newInstance(tc, wfo);
    log.info("Initialized workerFactory {}", wf);

    *//* Create Main Worker *//*
    final var wo = WorkerOptions.newBuilder()
                                .setMaxConcurrentActivityTaskPollers(8)
                                .setMaxTaskQueueActivitiesPerSecond(1000000)
                                .setMaxWorkerActivitiesPerSecond(100000)
                                .build();

    log.info("WorkerOption: {}l", wo);

    IntStream.range(0, 2).forEach(i ->
        wf.newWorker(Constants.TASK_QUEUE, wo)
            .registerActivitiesImplementations(
                accountingActivity,
                clearingActivity,
                clientResponseActivity,
                enrichActivity,
                fraudCheckActivity,
                limitCheckActivity,
                validateActivity
            )
    );
    *//* Start the worker *//*
    wf.start();
    log.info("Worker factory started");
    return wf;
  }*/
}
