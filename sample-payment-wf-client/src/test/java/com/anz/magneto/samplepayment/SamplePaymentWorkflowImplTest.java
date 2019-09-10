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
  public TestWatcher watchman = new TestWatcher() {
    @Override
    protected void failed(Throwable e, Description description) {
      if (testEnv != null) {
        log.warn("diagostics: {}", testEnv.getDiagnostics());
        testEnv.close();
      }
    }
  };

  private Worker worker;
  private WorkflowClient workflowClient;
  private ActivityCompletionClient completionClient;
  private WorkflowRequest workflowRequest;
  private ValidateActivity validateActivity;
  private EnrichActivity enrichActivity;
  private AccountingActivity accountingActivity;
  private FraudCheckActivity fraudCheckActivity;
  private SamplePaymentWorkflow samplePaymentWorkflow;

  @Before
  public void setUp() {
    testEnv = TestWorkflowEnvironment.newInstance();
    worker = testEnv.newWorker(Constants.TASK_LIST);
    worker.registerWorkflowImplementationTypes(SamplePaymentWorkflowImpl.class);
    workflowClient = testEnv.newWorkflowClient();
    completionClient = workflowClient.newActivityCompletionClient();

    samplePaymentWorkflow = workflowClient.newWorkflowStub(SamplePaymentWorkflow.class);
    workflowRequest = WorkflowRequest.builder().requestId("processPaymentWithMock").build();

    validateActivity = mock(ValidateActivity.class);
    enrichActivity = mock(EnrichActivity.class);
    accountingActivity = mock(AccountingActivity.class);
    fraudCheckActivity = mock(FraudCheckActivity.class);
    worker.registerActivitiesImplementations(
        validateActivity, enrichActivity, accountingActivity,
        fraudCheckActivity);

    when(validateActivity.validate(workflowRequest)).then(i -> {
      log.debug("validate");
      return WorkflowResponse.builder().status(Status.SUCCESS).build();
    });

    when(enrichActivity.enrich(workflowRequest)).then(i -> {
      log.debug("enrich");
      return workflowRequest;
    });

    when(accountingActivity.debitCustomerCreditFloat(workflowRequest)).then(i -> {
      log.debug("debitCustomerCreditFloat");
      return AccountingResponse.builder().accountingId("acctid").status(Status.SUCCESS).build();
    });

    testEnv.start();
  }

  @After
  public void tearDown() {
    testEnv.close();
  }

  @Test
  public void fraudCheckTimeOut()
      throws InterruptedException, TimeoutException, ExecutionException {

    when(fraudCheckActivity.fraudCheck(workflowRequest)).then(invocation -> {
      Activity.doNotCompleteOnReturn();
      log.debug("fraudCheck about to throw timeout");
      throw new SimulatedTimeoutException();
    });

    var f = WorkflowClient.execute(samplePaymentWorkflow::processPayment,
        workflowRequest);
    var response = f.get(2, TimeUnit.SECONDS);
    log.debug("workflowResponse {}", response);
    Assert.assertEquals("Fraud timedout", response.getMessage());
  }

  @Test
  public void fraudCheckPass()
      throws InterruptedException, TimeoutException, ExecutionException {

    when(fraudCheckActivity.fraudCheck(workflowRequest)).then(invocation -> {
      Activity.doNotCompleteOnReturn();
      byte[] taskToken = Activity.getTaskToken();
      ForkJoinPool.commonPool().execute(() -> {
        log.debug("About to complete fraud check");
        completionClient.complete(taskToken,
            WorkflowResponse.builder().status(Status.SUCCESS).message("No fraud found").build());
      });
      return null;
    });

    var f = WorkflowClient.execute(samplePaymentWorkflow::processPayment,
        workflowRequest);
    var response = f.get(2, TimeUnit.SECONDS);
    log.debug("workflowResponse {}", response);
    Assert.assertEquals("No fraud found", response.getMessage());
  }

  @Test
  public void stopProcessPaymentAfterEnrich()
      throws InterruptedException, TimeoutException, ExecutionException {

    when(enrichActivity.enrich(workflowRequest)).then(i -> {
      samplePaymentWorkflow.stopProcessPayment();
      return workflowRequest;
    });
    var f = WorkflowClient.execute(samplePaymentWorkflow::processPayment,
        workflowRequest);
    var response = f.get(2, TimeUnit.SECONDS);
    log.debug("workflowResponse {}", response);
    Assert.assertEquals("Workflow stopped after enrich", response.getMessage());
  }

  @Test
  public void testQueryCustomerDebitAccoutingResponse() {

    when(accountingActivity.debitCustomerCreditFloat(workflowRequest)).then(i -> {
      log.debug("debitCustomerCreditFloat");
      return AccountingResponse.builder().accountingId("testQuery").status(Status.SUCCESS).build();
    });

    var workflowExecution = WorkflowClient.start(
        samplePaymentWorkflow::processPayment, workflowRequest);
    var workflow = workflowClient
        .newWorkflowStub(SamplePaymentWorkflow.class, workflowExecution.getWorkflowId());
    var response = workflow.processPayment(null);
    log.debug("workflowResponse {}", response);
    Assert.assertEquals("testQuery", workflow.getCustomerDebitResponse().getAccountingId());
  }

}