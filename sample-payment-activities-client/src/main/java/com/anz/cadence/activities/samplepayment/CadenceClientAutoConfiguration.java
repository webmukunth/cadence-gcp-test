package com.anz.cadence.activities.samplepayment;

import com.anz.cadence.activities.samplepayment.accounting.AccountingActivity;
import com.anz.cadence.activities.samplepayment.clearing.ClearingActivity;
import com.anz.cadence.activities.samplepayment.clientresponse.ClientResponseActivity;
import com.anz.cadence.activities.samplepayment.enrich.EnrichActivity;
import com.anz.cadence.activities.samplepayment.fraudcheck.FraudCheckActivity;
import com.anz.cadence.activities.samplepayment.limitcheck.LimitCheckActivity;
import com.anz.cadence.activities.samplepayment.validate.ValidateActivity;
import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.autoconfigure.CadenceAutoConfiguration;
import com.uber.cadence.internal.worker.PollerOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker.Factory;
import com.uber.cadence.worker.Worker.FactoryOptions;
import com.uber.cadence.worker.WorkerOptions;
import com.uber.m3.tally.Scope;
import java.time.Duration;
import java.util.stream.IntStream;
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
  public Factory workerFactory(WorkflowServiceTChannel tc, Scope ms,
      AccountingActivity accountingActivity, ClearingActivity clearingActivity,
      ClientResponseActivity clientResponseActivity, EnrichActivity enrichActivity,
      FraudCheckActivity fraudCheckActivity, LimitCheckActivity limitCheckActivity,
      ValidateActivity validateActivity) {

    /* Create Factory */
    var fo = new FactoryOptions.Builder()
        .setMetricScope(ms)
        .build();

    var f = new Factory(tc, Constants.DOMAIN, fo);
    log.info("Initialized workerFactory {}", f);

    /* Create Main Worker */
    final var wo = new WorkerOptions.Builder()
        .setActivityPollerOptions(
            new PollerOptions.Builder()
                .setPollThreadCount(8)
                .setPollBackoffInitialInterval(Duration.ofMillis(50))
                .setPollBackoffMaximumInterval(Duration.ofMillis(500))
                .setPollThreadNamePrefix("Cadence Activity Poller")
                .build())
        .setMetricsScope(ms)
        .setTaskListActivitiesPerSecond(1000000)
        .setWorkerActivitiesPerSecond(100000)
        .setDisableWorkflowWorker(true)
        .build();

    log.info("WorkerOption: {}l", wo);

    IntStream.range(0, 2).forEach(i ->
        f.newWorker(Constants.TASK_LIST, wo)
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
    /* Start the worker */
    f.start();
    log.info("Worker factory started");
    return f;
  }
}
