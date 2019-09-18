package com.anz.cadence.workflow.sample2;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.anz.cadence.activities.sample2.accounting.AccountingActivity;
import com.anz.cadence.activities.sample2.accounting.AccountingResponse;
import com.anz.cadence.activities.sample2.accounting.AccountingStatus;
import com.anz.cadence.activities.sample2.clearing.ClearingActivity;
import com.anz.cadence.activities.sample2.clearing.ClearingResponse;
import com.anz.cadence.activities.sample2.clearing.ClearingStatus;
import com.anz.cadence.activities.sample2.clientresponse.ClientResponseActivity;
import com.anz.cadence.activities.sample2.enrich.EnrichActivity;
import com.anz.cadence.activities.sample2.fraudcheck.FraudCheckActivity;
import com.anz.cadence.activities.sample2.fraudcheck.FraudCheckOutcome;
import com.anz.cadence.activities.sample2.limitcheck.LimitCheckActivity;
import com.anz.cadence.activities.sample2.limitcheck.LimitCheckOutcome;
import com.anz.cadence.activities.sample2.validate.ValidateActivity;
import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.LimitType;
import com.anz.cadence.commons.api.workflow.StopWorkflowException;
import com.anz.cadence.commons.api.workflow.ValidationError;
import com.anz.cadence.commons.api.workflow.ValidationErrors;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.anz.cadence.commons.api.workflow.WorkflowResponse;
import com.uber.cadence.activity.Activity;
import com.uber.cadence.client.ActivityCompletionClient;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowFailureException;
import com.uber.cadence.testing.SimulatedTimeoutException;
import com.uber.cadence.testing.TestWorkflowEnvironment;
import com.uber.cadence.worker.Worker;
import java.time.Duration;
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
  private ClearingActivity clearingActivity;
  private WorkflowRequest workflowRequest;
  private AccountingResponse successAccountingResponse;

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
    clearingActivity = mock(ClearingActivity.class);

    Worker worker = testEnv.newWorker(Constants.TASK_LIST);
    worker.registerWorkflowImplementationTypes(SamplePaymentWorkflowImpl.class);
    worker.registerActivitiesImplementations(
        validateActivity, enrichActivity, accountingActivity, fraudCheckActivity,
        clientResponseActivity, limitCheckActivity, clearingActivity);

    testEnv.start();

    workflowRequest = WorkflowRequest.builder()
        .id(UUID.randomUUID().toString())
        .rqUID(UUID.randomUUID().toString())
        .clientName("junit")
        .build();

    successAccountingResponse = AccountingResponse.builder()
        .status(AccountingStatus.SUCCESS)
        .accountingId(UUID.randomUUID().toString())
        .paymentId(UUID.randomUUID().toString())
        .rqUID(UUID.randomUUID().toString())
        .build();

    when(validateActivity.validate(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
      log.debug("mock(validate): {}", request);
      return null;
    });

    when(enrichActivity.enrich(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
      log.debug("mock(enrich): {}", request);
      return request;
    });

    when(limitCheckActivity.limitCheck(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
      log.debug("mock(limitCheck): {}", request);
      return LimitCheckOutcome.PASS;
    });

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
      log.debug("mock(fraudCheck): {}", request);
      return FraudCheckOutcome.PASS;
    });

    when(accountingActivity.debitCustomerCreditFloat(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
      log.debug("mock(debitCustomerCreditFloat): {}", request);
      return successAccountingResponse;
    });

    when(accountingActivity.forceDebitCustomerCreditFloat(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
      log.debug("mock(forceDebitCustomerCreditFloat): {}", request);
      return successAccountingResponse;
    });

    when(accountingActivity.reverseDebitCustomerCreditFloat(any(), any())).then(i -> {
      WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
      AccountingResponse origResponse = i.getArgumentAt(1, AccountingResponse.class);
      log.debug("mock(reverseDebitCustomerCreditFloat): {} {}", request, origResponse);
      return successAccountingResponse;
    });

    when(clearingActivity.clearPayment(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
      log.debug("mock(clearingActivity): {}", request);
      return new ClearingResponse(ClearingStatus.CLEARED, "c1", "1", "1");
    });

    doAnswer(invocation -> {
      WorkflowRequest request = invocation.getArgumentAt(0, WorkflowRequest.class);
      WorkflowResponse response = invocation.getArgumentAt(1, WorkflowResponse.class);
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

    var ex = assertThrows(StopWorkflowException.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailureException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped due to fraudCheckOutcome: FAIL", ex.getMessage());
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

    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, workflowRequest);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("SUCCESS", response.getMessage());
  }

  @Test
  public void fraudCheckHoldThenTimeOut() {

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class))).then(invocation -> {
      log.debug("Sending {} response for fraudCheck activity", FraudCheckOutcome.HOLD);
      return FraudCheckOutcome.HOLD;
    });

    var ex = assertThrows(StopWorkflowException.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
        /* Advance the clock by 9 hours to timeout the wait after hold */
        testEnv.sleep(Duration.ofHours(9));
      } catch (WorkflowFailureException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped due to fraudCheckOutcome: HOLD_TIMEOUT", ex.getMessage());
  }

  @Test
  public void limitOnlyFail()
      throws InterruptedException, ExecutionException, TimeoutException {

    when(limitCheckActivity.limitCheck(any(WorkflowRequest.class))).then(invocation -> {
      log.debug("Sending {} response for limitCheck", LimitCheckOutcome.FAIL);
      return LimitCheckOutcome.FAIL;
    });

    var request = workflowRequest.withLimitType(LimitType.LIMITONLY);
    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, request);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals(LimitType.LIMITONLY, request.getLimitType());
    assertEquals("debitCustomer failed INSUFFICIENT_LIMIT", response.getMessage());
  }

  @Test
  public void afpThenLimitPass()
      throws InterruptedException, ExecutionException, TimeoutException {

    when(accountingActivity.debitCustomerCreditFloat(any(WorkflowRequest.class)))
        .then(invocation -> {
          log.debug("Sending {} response for debitCustomerCreditFloat",
              AccountingStatus.INSUFFICIENT_BALANCE);
          return AccountingResponse.builder()
              .status(AccountingStatus.INSUFFICIENT_BALANCE)
              .accountingId("a1")
              .rqUID("1")
              .paymentId("1")
              .build();
        });

    when(limitCheckActivity.limitCheck(any(WorkflowRequest.class))).then(invocation -> {
      log.debug("Sending {} response for limitCheck", LimitCheckOutcome.PASS);
      return LimitCheckOutcome.PASS;
    });

    when(accountingActivity.forceDebitCustomerCreditFloat(any(WorkflowRequest.class)))
        .then(invocation -> {
          log.debug("Sending {} response for forceDebitCustomerCreditFloat",
              AccountingStatus.SUCCESS);
          return successAccountingResponse;
        });

    var request = workflowRequest.withLimitType(LimitType.AFPTHENLIMIT);
    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, request);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals(LimitType.AFPTHENLIMIT, request.getLimitType());
    assertEquals("SUCCESS", response.getMessage());
  }

  @Test
  public void afpThenLimitFail()
      throws InterruptedException, ExecutionException, TimeoutException {

    when(accountingActivity.debitCustomerCreditFloat(any(WorkflowRequest.class)))
        .then(invocation -> {
          log.debug("Sending {} response for debitCustomerCreditFloat",
              AccountingStatus.INSUFFICIENT_BALANCE);
          return AccountingResponse.builder()
              .status(AccountingStatus.INSUFFICIENT_BALANCE)
              .accountingId("a1")
              .rqUID("1")
              .paymentId("1")
              .build();
        });

    when(limitCheckActivity.limitCheck(any(WorkflowRequest.class))).then(invocation -> {
      log.debug("Sending {} response for limitCheck", LimitCheckOutcome.FAIL);
      return LimitCheckOutcome.FAIL;
    });

    var request = workflowRequest.withLimitType(LimitType.AFPTHENLIMIT);
    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, request);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals(LimitType.AFPTHENLIMIT, request.getLimitType());
    assertEquals("debitCustomer failed INSUFFICIENT_LIMIT", response.getMessage());
  }

  @Test
  public void stopProcessAfterEnrich() {

    when(enrichActivity.enrich(any(WorkflowRequest.class))).then(invocation -> {
      samplePaymentWorkflow.stopProcessPayment();
      return invocation.getArgumentAt(0, WorkflowRequest.class);
    });

    var ex = assertThrows(StopWorkflowException.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailureException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped after enrich", ex.getMessage());
  }

  @Test
  public void stopProcessAfterDebitCustomer() {

    when(accountingActivity.debitCustomerCreditFloat(any(WorkflowRequest.class))).then(i -> {
      samplePaymentWorkflow.stopProcessPayment();
      log.debug("Sending {} response for forceDebitCustomerCreditFloat", AccountingStatus.SUCCESS);
      return successAccountingResponse;
    });

    var ex = assertThrows(StopWorkflowException.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailureException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped after debitCustomer", ex.getMessage());
  }

  @Test
  public void stopProcessAfterFraudCheck() {

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class))).then(invocation -> {
      samplePaymentWorkflow.stopProcessPayment();
      log.debug("mock(fraudCheckActivity): {}", FraudCheckOutcome.PASS);
      return FraudCheckOutcome.PASS;
    });

    var ex = assertThrows(StopWorkflowException.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailureException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped after fraudCheck", ex.getMessage());
  }

  @Test
  public void clearingRejected() {

    when(clearingActivity.clearPayment(any(WorkflowRequest.class))).then(i -> {
      log.debug("mock(clearingActivity): {}", ClearingStatus.REJECTED);
      return new ClearingResponse(ClearingStatus.REJECTED, "c1", "1", "1");
    });

    var ex = assertThrows(StopWorkflowException.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailureException e) {
        throw e.getCause();
      }
    });

    /* Ensure customer debit is reversed */
    verify(accountingActivity)
        .reverseDebitCustomerCreditFloat(eq(workflowRequest), anyObject());

    assertEquals("Stopped due to clearingStatus: REJECTED", ex.getMessage());
  }

  @Test
  public void clearingSubmittedAndTimeOut()
      throws InterruptedException, ExecutionException, TimeoutException {

    when(clearingActivity.clearPayment(any(WorkflowRequest.class))).then(i -> {
      log.debug("mock(clearingActivity): {}", ClearingStatus.SUBMITTED);
      return new ClearingResponse(ClearingStatus.SUBMITTED, "c1", "1", "1");
    });

    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, workflowRequest);
    /* Advance the clock by 3 days to timeout the wait*/
    testEnv.sleep(Duration.ofDays(3));
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("SUCCESS", response.getMessage());
  }

  @Test
  public void clearingSubmittedCleared()
      throws InterruptedException, ExecutionException, TimeoutException {

    when(clearingActivity.clearPayment(any(WorkflowRequest.class))).then(i -> {
      var workflowId = Activity.getWorkflowExecution().getWorkflowId();
      ForkJoinPool.commonPool().execute(() -> {
        var wfInstance = workflowClient.newWorkflowStub(SamplePaymentWorkflow.class, workflowId);
        wfInstance.paymentCleared();
      });

      log.debug("mock(clearingActivity): {}", ClearingStatus.SUBMITTED);
      return new ClearingResponse(ClearingStatus.SUBMITTED, "c1", "1", "1");
    });

    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, workflowRequest);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("SUCCESS", response.getMessage());
  }

  @Test
  public void clearingSubmittedStopped() {

    when(clearingActivity.clearPayment(any(WorkflowRequest.class))).then(i -> {
      var workflowId = Activity.getWorkflowExecution().getWorkflowId();
      ForkJoinPool.commonPool().execute(() -> {
        var wfInstance = workflowClient.newWorkflowStub(SamplePaymentWorkflow.class, workflowId);
        wfInstance.stopProcessPayment();
      });

      log.debug("mock(clearingActivity): {}", ClearingStatus.SUBMITTED);
      return new ClearingResponse(ClearingStatus.SUBMITTED, "c1", "1", "1");
    });

    var ex = assertThrows(StopWorkflowException.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailureException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped after clearing", ex.getMessage());
  }

  @Test
  public void testQueryCustomerDebitAccountingResponse() {

    when(accountingActivity.debitCustomerCreditFloat(any(WorkflowRequest.class)))
        .then(i -> successAccountingResponse);

    final var workflowExecution = WorkflowClient.start(
        samplePaymentWorkflow::submitPayment, workflowRequest);

    final var workflowInstance = workflowClient
        .newWorkflowStub(SamplePaymentWorkflow.class, workflowExecution.getWorkflowId());

    /* below invocation will not trigger the workflow, rather get the final response */
    final var response = workflowInstance.submitPayment(null);
    log.debug("workflowResponse {}", response);

    final var actualResponse = workflowInstance.getCustomerDebitResponse();
    assertEquals(successAccountingResponse, actualResponse);
  }
}