package com.anz.temporal.workflow.samplepayment;

import static com.anz.temporal.commons.Constants.TASK_QUEUE;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import com.anz.temporal.activities.samplepayment.accounting.AccountingActivity;
import com.anz.temporal.activities.samplepayment.accounting.AccountingResponse;
import com.anz.temporal.activities.samplepayment.accounting.AccountingStatus;
import com.anz.temporal.activities.samplepayment.clearing.ClearingActivity;
import com.anz.temporal.activities.samplepayment.clearing.ClearingResponse;
import com.anz.temporal.activities.samplepayment.clearing.ClearingStatus;
import com.anz.temporal.activities.samplepayment.clientresponse.ClientResponseActivity;
import com.anz.temporal.activities.samplepayment.enrich.EnrichActivity;
import com.anz.temporal.activities.samplepayment.fraudcheck.FraudCheckActivity;
import com.anz.temporal.activities.samplepayment.fraudcheck.FraudCheckOutcome;
import com.anz.temporal.activities.samplepayment.limitcheck.LimitCheckActivity;
import com.anz.temporal.activities.samplepayment.limitcheck.LimitCheckOutcome;
import com.anz.temporal.activities.samplepayment.validate.ValidateActivity;
import com.anz.temporal.commons.api.workflow.LimitType;
import com.anz.temporal.commons.api.workflow.StopWorkflowException;
import com.anz.temporal.commons.api.workflow.ValidationError;
import com.anz.temporal.commons.api.workflow.ValidationErrors;
import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import com.anz.temporal.commons.api.workflow.WorkflowResponse;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityOptions;
import io.temporal.client.*;
import io.temporal.api.enums.v1.TimeoutType;
import io.temporal.common.RetryOptions;
import io.temporal.common.converter.DefaultDataConverter;
import io.temporal.common.converter.JacksonJsonPayloadConverter;
import io.temporal.failure.ApplicationFailure;
import io.temporal.failure.SimulatedTimeoutFailure;
import io.temporal.failure.TimeoutFailure;
import io.temporal.testing.TestWorkflowEnvironment;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.temporal.testing.TestWorkflowEnvironmentInternal;
import io.temporal.worker.WorkerOptions;
import io.temporal.worker.WorkflowImplementationOptions;
import io.temporal.workflow.Async;
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
    workflowClient = testEnv.getWorkflowClient();

    completionClient = workflowClient.newActivityCompletionClient();
    samplePaymentWorkflow = workflowClient.newWorkflowStub(SamplePaymentWorkflow.class,WorkflowOptions.newBuilder()
            .setWorkflowExecutionTimeout(Duration.ofSeconds(5))
            .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(1).build())
            .setTaskQueue(TASK_QUEUE).build());

    validateActivity = mock(ValidateActivity.class, withSettings().withoutAnnotations());
    enrichActivity = mock(EnrichActivity.class, withSettings().withoutAnnotations());
    accountingActivity = mock(AccountingActivity.class, withSettings().withoutAnnotations());
    fraudCheckActivity = mock(FraudCheckActivity.class, withSettings().withoutAnnotations());
    limitCheckActivity = mock(LimitCheckActivity.class, withSettings().withoutAnnotations());
    clearingActivity = mock(ClearingActivity.class, withSettings().withoutAnnotations());
    clientResponseActivity = mock(ClientResponseActivity.class,
        withSettings().withoutAnnotations());


    final var w = testEnv.newWorker(TASK_QUEUE);
    w.registerWorkflowImplementationTypes(WorkflowImplementationOptions.newBuilder()
                    .setFailWorkflowExceptionTypes(StopWorkflowException.class).build(),
            SamplePaymentWorkflowImpl.class
        );

    w.registerActivitiesImplementations(
        validateActivity,
        enrichActivity,
        accountingActivity,
        fraudCheckActivity,
        clientResponseActivity,
        limitCheckActivity,
        clearingActivity
    );

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
      WorkflowRequest request = i.getArgument(0, WorkflowRequest.class);
      log.debug("mock(validate): {}", request);
      return Optional.empty();
    });

    when(enrichActivity.enrich(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgument(0, WorkflowRequest.class);
      log.debug("mock(enrich): {}", request);
      return request;
    });

    when(limitCheckActivity.limitCheck(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgument(0, WorkflowRequest.class);
      log.debug("mock(limitCheck): {}", request);
      return LimitCheckOutcome.PASS;
    });

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgument(0, WorkflowRequest.class);
      log.debug("mock(fraudCheck): {}", request);
      return FraudCheckOutcome.PASS;
    });

    when(accountingActivity.debitCustomerCreditFloat(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgument(0, WorkflowRequest.class);
      log.debug("mock(debitCustomerCreditFloat): {}", request);
      return successAccountingResponse;
    });

    when(accountingActivity.forceDebitCustomerCreditFloat(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgument(0, WorkflowRequest.class);
      log.debug("mock(forceDebitCustomerCreditFloat): {}", request);
      return successAccountingResponse;
    });

    when(accountingActivity.reverseDebitCustomerCreditFloat(any(), any())).then(i -> {
      WorkflowRequest request = i.getArgument(0, WorkflowRequest.class);
      AccountingResponse origResponse = i.getArgument(1, AccountingResponse.class);
      log.debug("mock(reverseDebitCustomerCreditFloat): {} {}", request, origResponse);
      return successAccountingResponse;
    });

    when(clearingActivity.clearPayment(any(WorkflowRequest.class))).then(i -> {
      WorkflowRequest request = i.getArgument(0, WorkflowRequest.class);
      log.debug("mock(clearingActivity): {}", request);
      return new ClearingResponse(ClearingStatus.CLEARED, "c1", "1", "1");
    });

    doAnswer(invocation -> {
      WorkflowRequest request = invocation.getArgument(0, WorkflowRequest.class);
      WorkflowResponse response = invocation.getArgument(1, WorkflowResponse.class);
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
            .build().getOptionalErrors());

    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, workflowRequest);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("Validation failed", response.getMessage());
    assertEquals(2, response.getValidationErrors().get().size());
  }

  @Test
  public void fraudCheckTimeOut()
      throws InterruptedException, TimeoutException, ExecutionException {

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class))).then(invocation -> {
      Activity.getExecutionContext().doNotCompleteOnReturn();
        log.debug("fraudCheck about to throw timeout");
        throw new TimeoutFailure("simulated",null,TimeoutType.TIMEOUT_TYPE_START_TO_CLOSE);
    });
    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, workflowRequest);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("SUCCESS", response.getMessage());
  }

  @Test
  public void fraudCheckPassResponse()
      throws InterruptedException, TimeoutException, ExecutionException {

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class))).then(invocation -> {
      Activity.getExecutionContext().doNotCompleteOnReturn();
      byte[] taskToken = Activity.getExecutionContext().getTaskToken();
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
      Activity.getExecutionContext().doNotCompleteOnReturn();
      byte[] taskToken = Activity.getExecutionContext().getTaskToken();
      ForkJoinPool.commonPool().execute(() -> {
        log.debug("Sending {} response for fraudCheck activity", FraudCheckOutcome.FAIL);
        completionClient.complete(taskToken, FraudCheckOutcome.FAIL);
      });
      return null;
    });

    var ex = assertThrows(ApplicationFailure.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailedException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped due to fraudCheckOutcome: FAIL", ex.getOriginalMessage());
  }

  @Test
  public void fraudCheckHoldThenRelease()
      throws InterruptedException, ExecutionException, TimeoutException {

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class))).then(invocation -> {
      log.debug("Sending {} response for fraudCheck activity", FraudCheckOutcome.HOLD);
      var workflowId = Activity.getExecutionContext().getInfo().getWorkflowId();
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

    var ex = assertThrows(TimeoutFailure.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
        /* Advance the clock by 9 hours to timeout the wait after hold */
        testEnv.sleep(Duration.ofHours(9));
      } catch (WorkflowFailedException e) {
        throw e.getCause();
      }
    });
    assertEquals("timeoutType=TIMEOUT_TYPE_START_TO_CLOSE", ex.getMessage());
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
      return invocation.getArgument(0, WorkflowRequest.class);
    });

    var ex = assertThrows(ApplicationFailure.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailedException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped after enrich", ex.getOriginalMessage());
  }

  @Test
  public void stopProcessAfterDebitCustomer() {

    when(accountingActivity.debitCustomerCreditFloat(any(WorkflowRequest.class))).then(i -> {
      samplePaymentWorkflow.stopProcessPayment();
      log.debug("Sending {} response for forceDebitCustomerCreditFloat", AccountingStatus.SUCCESS);
      return successAccountingResponse;
    });

    var ex = assertThrows(ApplicationFailure.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailedException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped after debitCustomer", ex.getOriginalMessage());
  }

  @Test
  public void stopProcessAfterFraudCheck() {

    when(fraudCheckActivity.fraudCheck(any(WorkflowRequest.class))).then(invocation -> {
      samplePaymentWorkflow.stopProcessPayment();
      log.debug("mock(fraudCheckActivity): {}", FraudCheckOutcome.PASS);
      return FraudCheckOutcome.PASS;
    });

    var ex = assertThrows(ApplicationFailure.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailedException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped after fraudCheck", ex.getOriginalMessage());
  }

  @Test
  public void clearingRejected() {

    when(clearingActivity.clearPayment(any(WorkflowRequest.class))).then(i -> {
      log.debug("mock(clearingActivity): {}", ClearingStatus.REJECTED);
      return new ClearingResponse(ClearingStatus.REJECTED, "c1", "1", "1");
    });

    var ex = assertThrows(ApplicationFailure.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailedException e) {
        throw e.getCause();
      }
    });

    /* Ensure customer debit is reversed */
    verify(accountingActivity)
        .reverseDebitCustomerCreditFloat(eq(workflowRequest), anyObject());

    assertEquals("Stopped due to clearingStatus: REJECTED", ex.getOriginalMessage());
  }

  @Test
  public void clearingSubmittedAndTimeOut()
      throws InterruptedException, ExecutionException, TimeoutException {

    when(clearingActivity.clearPayment(any(WorkflowRequest.class))).then(i -> {
      log.debug("mock(clearingActivity): {}", ClearingStatus.SUBMITTED);
      samplePaymentWorkflow.paymentCleared();
      return new ClearingResponse(ClearingStatus.SUBMITTED, "c1", "1", "1");
    });

    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, workflowRequest);
    /* Advance the clock by 3 days to timeout the wait*/
    //testEnv.sleep(Duration.ofDays(3));

    var response = f.get(2,TimeUnit.SECONDS);
    assertEquals("SUCCESS", response.getMessage());
  }

  @Test
  public void clearingSubmittedCleared()
      throws InterruptedException, ExecutionException, TimeoutException {

    when(clearingActivity.clearPayment(any(WorkflowRequest.class))).then(i -> {
      var workflowId = Activity.getExecutionContext().getInfo().getWorkflowId();
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
      var workflowId = Activity.getExecutionContext().getInfo().getWorkflowId();
      ForkJoinPool.commonPool().execute(() -> {
        var wfInstance = workflowClient.newWorkflowStub(SamplePaymentWorkflow.class, workflowId);
        wfInstance.stopProcessPayment();
      });

      log.debug("mock(clearingActivity): {}", ClearingStatus.SUBMITTED);
      return new ClearingResponse(ClearingStatus.SUBMITTED, "c1", "1", "1");
    });

    var ex = assertThrows(ApplicationFailure.class, () -> {
      try {
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailedException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped after clearing", ex.getOriginalMessage());
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