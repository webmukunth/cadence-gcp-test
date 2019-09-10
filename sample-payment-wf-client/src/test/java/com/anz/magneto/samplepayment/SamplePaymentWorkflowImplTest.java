package com.anz.magneto.samplepayment;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.anz.magneto.activites.accounting.AccountingActivity;
import com.anz.magneto.activites.accounting.AccountingResponse;
import com.anz.magneto.activites.enrich.EnrichActivity;
import com.anz.magneto.activites.fraudcheck.FraudCheckActivity;
import com.anz.magneto.activites.validate.ValidateActivity;
import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.Status;
import com.anz.magneto.commons.api.WorkflowRequest;
import com.anz.magneto.commons.api.WorkflowResponse;
import com.uber.cadence.activity.Activity;
import com.uber.cadence.client.ActivityCompletionClient;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.testing.SimulatedTimeoutException;
import com.uber.cadence.testing.TestWorkflowEnvironment;
import com.uber.cadence.worker.Worker;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
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
  private ActivityCompletionClient completionClient;

  @Before
  public void setUp() {
    testEnv = TestWorkflowEnvironment.newInstance();
    worker = testEnv.newWorker(Constants.TASK_LIST);
    worker.registerWorkflowImplementationTypes(SamplePaymentWorkflowImpl.class);
    workflowClient = testEnv.newWorkflowClient();
    completionClient = workflowClient.newActivityCompletionClient();
  }

  @After
  public void tearDown() {
    testEnv.close();
  }

  @Test
  public void fraudCheckTimeOut()
      throws InterruptedException, TimeoutException, ExecutionException {

    var workflowRequest = WorkflowRequest.builder().requestId("processPaymentWithMock").build();

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
      throw new SimulatedTimeoutException();
    });

    testEnv.start();

    var samplePaymentWorkflow = workflowClient.newWorkflowStub(SamplePaymentWorkflow.class);

    var future =
        WorkflowClient.execute(samplePaymentWorkflow::processPayment, workflowRequest);

    var response = future.get(2, TimeUnit.SECONDS);
    log.debug("workflow {}", response);

    Assert.assertEquals("Fraud timedout", response.getMessage());
  }

  @Test
  public void fraudCheckPass()
      throws InterruptedException, TimeoutException, ExecutionException {

    var workflowRequest = WorkflowRequest.builder().requestId("processPaymentWithMock").build();

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

      byte[] taskToken = Activity.getTaskToken();
      ForkJoinPool.commonPool().execute(() -> {
        log.info("completing fraudCheck");
        completionClient.complete(taskToken,
            WorkflowResponse.builder().status(Status.SUCCESS).message("No fraud found").build());
      });
      return null;
    });

    testEnv.start();

    var samplePaymentWorkflow = workflowClient.newWorkflowStub(SamplePaymentWorkflow.class);

    var future =
        WorkflowClient.execute(samplePaymentWorkflow::processPayment, workflowRequest);

    var response = future.get(2, TimeUnit.SECONDS);
    log.debug("workflow {}", response);

    Assert.assertEquals("No fraud found", response.getMessage());
  }
}