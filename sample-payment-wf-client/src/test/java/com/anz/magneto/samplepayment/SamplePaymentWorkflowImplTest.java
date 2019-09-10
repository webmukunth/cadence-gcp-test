package com.anz.magneto.samplepayment;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.anz.magneto.activites.accounting.AccountingActivity;
import com.anz.magneto.activites.accounting.AccountingActivityImpl;
import com.anz.magneto.activites.accounting.AccountingResponse;
import com.anz.magneto.activites.enrich.EnrichActivity;
import com.anz.magneto.activites.enrich.EnrichActivityImpl;
import com.anz.magneto.activites.fraudcheck.FraudCheckActivity;
import com.anz.magneto.activites.fraudcheck.FraudCheckActivityImpl;
import com.anz.magneto.activites.validate.ValidateActivity;
import com.anz.magneto.activites.validate.ValidateActivityImpl;
import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.Status;
import com.anz.magneto.commons.api.WorkflowRequest;
import com.anz.magneto.commons.api.WorkflowResponse;
import com.uber.cadence.ListOpenWorkflowExecutionsRequest;
import com.uber.cadence.WorkflowExecutionFilter;
import com.uber.cadence.WorkflowExecutionInfo;
import com.uber.cadence.activity.Activity;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.testing.TestWorkflowEnvironment;
import com.uber.cadence.worker.Worker;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

@Slf4j
public class SamplePaymentWorkflowImplTest {

  private TestWorkflowEnvironment testEnv;
  /**
   * Prints a history of the workflow under test in case of a test failure.
   */
  @Rule
  public TestWatcher watchman =
      new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
          if (testEnv != null) {
            log.debug("diagostics: {}",
                testEnv.getDiagnostics());
            testEnv.close();
          }
        }
      };
  private Worker worker;
  private WorkflowClient workflowClient;

  @Before
  public void setUp() {
    testEnv = TestWorkflowEnvironment.newInstance();
    worker = testEnv.newWorker(Constants.TASK_LIST);
    worker.registerWorkflowImplementationTypes(SamplePaymentWorkflowImpl.class);
    workflowClient = testEnv.newWorkflowClient();
  }

  @After
  public void tearDown() {
    testEnv.close();
  }

  @Test
  public void processPaymentWithMock() throws TException, InterruptedException {
    var workflowRequest = WorkflowRequest.builder().requestId("processPaymentWithMock")
        .build();

    var validateActivity = mock(ValidateActivity.class);
    var enrichActivity = mock(EnrichActivity.class);
    var accountingActivity = mock(AccountingActivity.class);
    var fraudCheckActivity = mock(FraudCheckActivity.class);

    worker.registerActivitiesImplementations(validateActivity, enrichActivity,
        accountingActivity, fraudCheckActivity);

    when(validateActivity.validate(workflowRequest)).then(i -> {
      log.info("validate");
      return WorkflowResponse.builder()
          .status(Status.SUCCESS)
          .build();
    });

    when(enrichActivity.enrich(workflowRequest)).then(i -> {
      log.info("enrich");
      return workflowRequest;
    });

    when(accountingActivity.debitCustomerCreditFloat(workflowRequest)).then(i -> {
      log.info("debitCustomerCreditFloat");
      return AccountingResponse.builder()
          .accountingId("acctid")
          .status(Status.SUCCESS)
          .build();
    });

    when(fraudCheckActivity.fraudCheck(workflowRequest)).then(invocation -> {
      log.info("fraudCheck doNotCompleteOnReturn");
      Activity.doNotCompleteOnReturn();
      return null;
    });
    testEnv.start();

    var samplePaymentWorkflow = workflowClient
        .newWorkflowStub(SamplePaymentWorkflow.class);
    var workflowExecution =
        WorkflowClient.start(samplePaymentWorkflow::processPayment, workflowRequest);

    testEnv.sleep(Duration.ofSeconds(180));
    var workflowExecutionsRequest = new ListOpenWorkflowExecutionsRequest()
        .setDomain(testEnv.getDomain())
        .setExecutionFilter(
            new WorkflowExecutionFilter().setWorkflowId(workflowExecution.getWorkflowId()));

    var workflowExecutionsResponse =
        testEnv.getWorkflowService().ListOpenWorkflowExecutions(workflowExecutionsRequest);

    log.debug("openWorkflows {}", workflowExecutionsResponse);

    for (WorkflowExecutionInfo executionInfo : workflowExecutionsResponse.getExecutions()) {
      log.debug("openWorkflow-executionInfo: {}", executionInfo);
    }

    Thread.sleep(Duration.ofMinutes(3).toMillis());
  }


  @Test
  public void processPayment() {
    var workflowRequest = WorkflowRequest.builder().requestId("processPayment").build();

    var validateActivity = new ValidateActivityImpl();
    var enrichActivity = new EnrichActivityImpl();
    var accountingActivity = new AccountingActivityImpl();
    var fraudCheckActivity = new FraudCheckActivityImpl();

    worker.registerActivitiesImplementations(validateActivity, enrichActivity,
        accountingActivity, fraudCheckActivity);
    testEnv.start();

    var samplePaymentWorkflow = workflowClient
        .newWorkflowStub(SamplePaymentWorkflow.class);

    var workflowExecution =
        WorkflowClient.start(samplePaymentWorkflow::processPayment, workflowRequest);

    log("Diagnostics after start {}", testEnv.getDiagnostics());
    testEnv.sleep(Duration.ofMinutes(1));
    log("Diagnostics after sleep {}", testEnv.getDiagnostics());

    /*
    var workflowExecutionsResponse =
        testEnv.getWorkflowService().ListOpenWorkflowExecutions(
            new ListOpenWorkflowExecutionsRequest()
                .setDomain(testEnv.getDomain())
                .setExecutionFilter(
                    new WorkflowExecutionFilter()
                        .setWorkflowId(workflowExecution.getWorkflowId())));

    log.debug("openWorkflows {}", workflowExecutionsResponse);

    for (WorkflowExecutionInfo executionInfo : workflowExecutionsResponse.getExecutions()) {
      log.debug("openWorkflow-executionInfo: {}", executionInfo);
    }

     */
    testEnv.awaitTermination(5, TimeUnit.MINUTES);
  }

  private String currentTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
        .withZone(ZoneId.systemDefault());
    return formatter.format(Instant.ofEpochMilli(testEnv.currentTimeMillis()));
  }

  private void log(String message, Object... args) {
    log.debug("[{}] " + message, currentTime(), args);
  }
}