package com.anz.magneto.activites;

import com.anz.magneto.activites.accounting.AccountingActivity;
import com.anz.magneto.activites.clearing.ClearingActivity;
import com.anz.magneto.activites.clientresponse.ClientResponseActivity;
import com.anz.magneto.activites.enrich.EnrichActivity;
import com.anz.magneto.activites.fraudcheck.FraudCheckActivity;
import com.anz.magneto.activites.limitcheck.LimitCheckActivity;
import com.anz.magneto.activites.validate.ValidateActivity;
import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.autoconfigure.CadenceAutoConfiguration;
import com.uber.cadence.internal.worker.PollerOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker.Factory;
import com.uber.cadence.worker.Worker.FactoryOptions;
import com.uber.cadence.worker.WorkerOptions;
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
  public Factory workerFactory(WorkflowServiceTChannel tc, Scope ms,
      AccountingActivity accountingActivity, ClearingActivity clearingActivity,
      ClientResponseActivity clientResponseActivity, EnrichActivity enrichActivity,
      FraudCheckActivity fraudCheckActivity, LimitCheckActivity limitCheckActivity,
      ValidateActivity validateActivity) {

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
            .setActivityPollerOptions(
                new PollerOptions.Builder()
                    .setPollThreadCount(1)
                    .setMaximumPollRateIntervalMilliseconds(1000)
                    .setPollThreadNamePrefix("Cadence Activity Poller")
                    .build())
            .setMaxConcurrentActivityExecutionSize(16)
            .setIdentity(applicationName + "@" + Constants.INSTANCE_NAME)
            .setMetricsScope(ms)
            .setDisableWorkflowWorker(true)
            .build()
    );

    log.info("Initialized worker {}", worker);

    /* Register Download Activity  with worker */
    worker.registerActivitiesImplementations(
        accountingActivity, clearingActivity, clientResponseActivity, enrichActivity,
        fraudCheckActivity, limitCheckActivity, validateActivity);
    /* Start the worker */
    f.start();
    log.info("Worker factory started");
    return f;
  }
}
