package com.anz.magneto.samplepayment;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.anz.magneto.activites.accounting.AccountingActivity;
import com.anz.magneto.activites.accounting.AccountingResponse;
import com.anz.magneto.activites.accounting.AccountingStatus;
import com.anz.magneto.activites.clientresponse.ClientResponseActivity;
import com.anz.magneto.activites.enrich.EnrichActivity;
import com.anz.magneto.activites.fraudcheck.FraudCheckActivity;
import com.anz.magneto.activites.fraudcheck.FraudCheckOutcome;
import com.anz.magneto.activites.limitcheck.LimitCheckActivity;
import com.anz.magneto.activites.limitcheck.LimitCheckOutcome;
import com.anz.magneto.activites.validate.ValidateActivity;
import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.workflow.StopWorkflowException;
import com.anz.magneto.commons.api.workflow.ValidationError;
import com.anz.magneto.commons.api.workflow.ValidationErrors;
import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.anz.magneto.commons.api.workflow.WorkflowResponse;
import com.uber.cadence.activity.Activity;
import com.uber.cadence.client.ActivityCompletionClient;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowFailureException;
import com.uber.cadence.testing.SimulatedTimeoutException;
import com.uber.cadence.testing.TestWorkflowEnvironment;
import com.uber.cadence.worker.Worker;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class SamplePaymentWorkflowImplTest {

  private TestWorkflowEnvironment testEnv;
  private WorkflowClient workflowClient;
  private ActivityCompletionClient completionClient;
  private SamplePaymentWorkflow samplePaymentWorkflow;

  private ValidateActivity validateActivity;
  private EnrichActivity enrichActivity;
  private AccountingActivity accountingActivity;
  private FraudCheckActivity fraudCheckActivity;
  private ClientResponseActivity clientResponseActivity;
  private LimitCheckActivity limitCheckActivity;

  @Before
  public void setUp() {
    testEnv = TestWorkflowEnvironment.newInstance();
    workflowClient = testEnv.newWorkflowClient();
    completionClient = workflowClient.newActivityCompletionClient();
    samplePaymentWorkflow = workflowClient.newWorkflowStub(SamplePaymentWorkflow.class);

    validateActivity = mock(ValidateActivity.class);
    enrichActivity = mock(EnrichActivity.class);
    accountingActivity = mock(AccountingActivity.class);
    fraudCheckActivity = mock(FraudCheckActivity.class);
    clientResponseActivity = mock(ClientResponseActivity.class);
    limitCheckActivity = mock(LimitCheckActivity.class);

    Worker worker = testEnv.newWorker(Constants.TASK_LIST);
    worker.registerWorkflowImplementationTypes(SamplePaymentWorkflowImpl.class);
    worker.registerActivitiesImplementations(
        validateActivity, enrichActivity, accountingActivity,
        fraudCheckActivity, clientResponseActivity, limitCheckActivity);

    testEnv.start();

    when(validateActivity.validate(any(WorkflowRequest.class)))
        .then(i -> {
          WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
          log.debug("mock(validate): {}", request);
          return null;
        });

    when(enrichActivity.enrich(any(WorkflowRequest.class)))
        .then(i -> {
          WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
          log.debug("mock(enrich): {}", request);
          return request;
        });

    when(limitCheckActivity.limitCheck(any(WorkflowRequest.class)))
        .then(i -> {
          WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
          log.debug("mock(limitCheck): {}", request);
          return LimitCheckOutcome.PASS;
        });

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class)))
        .then(i -> {
          WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
          log.debug("mock(fraudCheck): {}", request);
          return FraudCheckOutcome.PASS;
        });

    when(accountingActivity.debitCustomerCreditFloat(any(WorkflowRequest.class)))
        .then(i -> {
          WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
          log.debug("mock(debitCustomerCreditFloat): {}", request);
          return new AccountingResponse(AccountingStatus.SUCCESS, UUID.randomUUID().toString());
        });

    /* Mock as well as assert the final response to be success */
    doAnswer(invocation -> {
      WorkflowRequest request = (WorkflowRequest) invocation.getArguments()[0];
      WorkflowResponse response = (WorkflowResponse) invocation.getArguments()[1];
      log.debug("mock(clientResponse) response={} request={}", response, request);
      return null;
    }).when(clientResponseActivity)
        .sendResponse(any(WorkflowRequest.class), any(WorkflowResponse.class));
  }

  @After
  public void tearDown() {
    testEnv.close();
  }

  @Test
  public void successfulPayment()
      throws InterruptedException, ExecutionException, TimeoutException {
    var workflowRequest = WorkflowRequest.builder().requestId("successfulPayment").build();
    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, workflowRequest);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("SUCCESS", response.getMessage());
  }

  @Test
  public void validationErrors()
      throws InterruptedException, TimeoutException, ExecutionException {

    when(validateActivity.validate(any(WorkflowRequest.class)))
        .then(invocation -> ValidationErrors.builder()
            .error(new ValidationError("c1", "c1 failed"))
            .error(new ValidationError("c2", "c2 failed"))
            .build().getErrors());

    var workflowRequest = WorkflowRequest.builder().requestId("validationErrors").build();
    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, workflowRequest);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("Validation failed", response.getMessage());
    assertEquals(2, response.getValidationErrors().size());
  }

  @Test
  public void fraudCheckTimeOut()
      throws InterruptedException, TimeoutException, ExecutionException {

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class))).then(invocation -> {
      Activity.doNotCompleteOnReturn();
      log.debug("fraudCheck about to throw timeout");
      throw new SimulatedTimeoutException();
    });

    var workflowRequest = WorkflowRequest.builder().requestId("fraudCheckTimeOut").build();
    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, workflowRequest);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("SUCCESS", response.getMessage());
  }

  @Test
  public void fraudCheckPassResponse()
      throws InterruptedException, TimeoutException, ExecutionException {

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class))).then(invocation -> {
      Activity.doNotCompleteOnReturn();
      byte[] taskToken = Activity.getTaskToken();
      ForkJoinPool.commonPool().execute(() -> {
        log.debug("Sending {} response for fraudCheck activity", FraudCheckOutcome.PASS);
        completionClient.complete(taskToken, FraudCheckOutcome.PASS);
      });
      return null;
    });

    var workflowRequest = WorkflowRequest.builder().requestId("fraudCheckPassResponse").build();
    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, workflowRequest);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("SUCCESS", response.getMessage());
  }

  @Test
  public void fraudCheckFailResponse() {

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class))).then(invocation -> {
      Activity.doNotCompleteOnReturn();
      byte[] taskToken = Activity.getTaskToken();
      ForkJoinPool.commonPool().execute(() -> {
        log.debug("Sending {} response for fraudCheck activity", FraudCheckOutcome.FAIL);
        completionClient.complete(taskToken, FraudCheckOutcome.FAIL);
      });
      return null;
    });

    assertThrows(StopWorkflowException.class, () -> {
      try {
        var workflowRequest = WorkflowRequest.builder().requestId("fraudCheckFailResponse").build();
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailureException e) {
        throw e.getCause();
      }
    });
  }

  @Test
  public void fraudCheckHoldThenRelease()
      throws InterruptedException, ExecutionException, TimeoutException {

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class))).then(invocation -> {
      log.debug("Sending {} response for fraudCheck activity", FraudCheckOutcome.HOLD);
      var workflowId = Activity.getWorkflowExecution().getWorkflowId();
      ForkJoinPool.commonPool().execute(() -> {
        var wfInstance = workflowClient.newWorkflowStub(SamplePaymentWorkflow.class, workflowId);
        wfInstance.releaseFraudCheckHold();
      });
      return FraudCheckOutcome.HOLD;
    });

    var wfRequest = WorkflowRequest.builder().requestId("fraudCheckHoldThenRelease").build();
    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, wfRequest);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("SUCCESS", response.getMessage());
  }

  /*
  @Test
  public void stopProcessPaymentAfterEnrich()
      throws InterruptedException, TimeoutException, ExecutionException {

    when(enrichActivity.enrich(workflowRequest)).then(i -> {
      samplePaymentWorkflow.stopProcessPayment();
      return workflowRequest;
    });
    final var f = WorkflowClient.execute(samplePaymentWorkflow::processPayment,
        workflowRequest);
    final var response = f.get(2, TimeUnit.SECONDS);
    log.debug("workflowResponse {}", response);
    Assert.assertEquals("Workflow stopped after enrich", response.getMessage());
  }

  @Test
  public void testQueryCustomerDebitAccoutingResponse() {

    when(accountingActivity.debitCustomerCreditFloat(workflowRequest)).then(i -> {
      log.debug("debitCustomerCreditFloat");
      return AccountingResponse.builder().accountingId("testQuery").status(WorkflowStatus.SUCCESS)
          .build();
    });

    final var workflowExecution = WorkflowClient.start(
        samplePaymentWorkflow::processPayment, workflowRequest);
    final var workflow = workflowClient
        .newWorkflowStub(SamplePaymentWorkflow.class, workflowExecution.getWorkflowId());
    final var response = workflow.processPayment(null);
    log.debug("workflowResponse {}", response);
    Assert.assertEquals("testQuery", workflow.getCustomerDebitResponse().getAccountingId());
  }
   */

  /*
   * Prints a history of the workflow under test in case of a test failure.
   *
   * @Rule public TestWatcher watchman = new TestWatcher() {
   * @Override protected void failed(Throwable e, Description description) { if (testEnv != null) {
   * log.warn("diagostics: {}", testEnv.getDiagnostics()); testEnv.close(); } } };
   */

}