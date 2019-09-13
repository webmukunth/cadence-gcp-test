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
import com.anz.magneto.activites.clearing.ClearingActivity;
import com.anz.magneto.activites.clearing.ClearingResponse;
import com.anz.magneto.activites.clearing.ClearingStatus;
import com.anz.magneto.activites.clientresponse.ClientResponseActivity;
import com.anz.magneto.activites.enrich.EnrichActivity;
import com.anz.magneto.activites.fraudcheck.FraudCheckActivity;
import com.anz.magneto.activites.fraudcheck.FraudCheckOutcome;
import com.anz.magneto.activites.limitcheck.LimitCheckActivity;
import com.anz.magneto.activites.limitcheck.LimitCheckOutcome;
import com.anz.magneto.activites.validate.ValidateActivity;
import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.api.workflow.LimitType;
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
        validateActivity, enrichActivity, accountingActivity,
        fraudCheckActivity, clientResponseActivity, limitCheckActivity,
        clearingActivity);

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

    when(clearingActivity.clearPayment(any(WorkflowRequest.class)))
        .then(i -> {
          WorkflowRequest request = i.getArgumentAt(0, WorkflowRequest.class);
          log.debug("mock(clearingActivity): {}", request);
          return new ClearingResponse(ClearingStatus.CLEARED, "c1");
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

    var ex = assertThrows(StopWorkflowException.class, () -> {
      try {
        var workflowRequest = WorkflowRequest.builder().requestId("fraudCheckFailResponse").build();
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

    var wfRequest = WorkflowRequest.builder().requestId("fraudCheckHoldThenRelease").build();
    var f = WorkflowClient.execute(samplePaymentWorkflow::submitPayment, wfRequest);
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
        var workflowRequest = WorkflowRequest.builder().requestId("fraudCheckHoldThenRelease")
            .build();
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

    var request = WorkflowRequest.builder()
        .requestId("limitOnlyFail")
        .limitType(LimitType.LIMITONLY)
        .build();
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
          return AccountingResponse.builder()
              .status(AccountingStatus.SUCCESS)
              .accountingId("f1-a1")
              .build();
        });

    var request = WorkflowRequest.builder()
        .requestId("afpThenLimitPass")
        .limitType(LimitType.AFPTHENLIMIT)
        .build();
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
              .build();
        });

    when(limitCheckActivity.limitCheck(any(WorkflowRequest.class))).then(invocation -> {
      log.debug("Sending {} response for limitCheck", LimitCheckOutcome.FAIL);
      return LimitCheckOutcome.FAIL;
    });

    var request = WorkflowRequest.builder()
        .requestId("afpThenLimitFail")
        .limitType(LimitType.AFPTHENLIMIT)
        .build();
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
        var workflowRequest = WorkflowRequest.builder().requestId("stopProcessAfterEnrich").build();
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailureException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped after enrich", ex.getMessage());
  }

  @Test
  public void stopProcessAfterDebitCustomer() {

    when(accountingActivity.debitCustomerCreditFloat(any(WorkflowRequest.class)))
        .then(invocation -> {
          samplePaymentWorkflow.stopProcessPayment();

          log.debug("Sending {} response for forceDebitCustomerCreditFloat",
              AccountingStatus.SUCCESS);
          return AccountingResponse.builder()
              .status(AccountingStatus.SUCCESS)
              .accountingId("f1-a1")
              .build();
        });

    var ex = assertThrows(StopWorkflowException.class, () -> {
      try {
        var workflowRequest = WorkflowRequest.builder().requestId("stopProcessAfterEnrich").build();
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
      return FraudCheckOutcome.PASS;
    });

    var ex = assertThrows(StopWorkflowException.class, () -> {
      try {
        var workflowRequest = WorkflowRequest.builder().requestId("stopProcessAfterEnrich").build();
        samplePaymentWorkflow.submitPayment(workflowRequest);
      } catch (WorkflowFailureException e) {
        throw e.getCause();
      }
    });
    assertEquals("Stopped after fraudCheck", ex.getMessage());
  }

  @Test
  public void testQueryCustomerDebitAccoutingResponse() {

    var accoutingResponse = AccountingResponse.builder()
        .status(AccountingStatus.SUCCESS)
        .accountingId("testQueryCustomerDebitAccoutingResponse")
        .build();

    when(accountingActivity.debitCustomerCreditFloat(any(WorkflowRequest.class)))
        .then(i -> accoutingResponse);

    var workflowRequest = WorkflowRequest.builder().requestId("testQuery").build();

    final var workflowExecution = WorkflowClient.start(
        samplePaymentWorkflow::submitPayment, workflowRequest);

    final var workflowInstance = workflowClient
        .newWorkflowStub(SamplePaymentWorkflow.class, workflowExecution.getWorkflowId());

    /* below invocation will not trigger the workflow, rather get the final response */
    final var response = workflowInstance.submitPayment(null);
    log.debug("workflowResponse {}", response);

    final var actualResponse = workflowInstance.getCustomerDebitResponse();
    assertEquals(accoutingResponse, actualResponse);
  }
}