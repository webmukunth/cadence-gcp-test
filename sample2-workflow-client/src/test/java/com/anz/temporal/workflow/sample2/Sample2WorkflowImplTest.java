package com.anz.temporal.workflow.sample2;

import static com.anz.temporal.commons.Constants.TASK_QUEUE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import com.anz.temporal.activities.sample2.ServiceAActivity;
import com.anz.temporal.activities.sample2.ServiceBActivity;
import com.anz.temporal.commons.Constants;
import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.common.RetryOptions;
import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.worker.Worker;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class Sample2WorkflowImplTest {

  private TestWorkflowEnvironment testEnv;
  private WorkflowClient workflowClient;

  private Sample2Workflow sample2Workflow;
  private ServiceAActivity serviceAActivity;
  private ServiceBActivity serviceBActivity;
  private WorkflowRequest workflowRequest;


  @Before
  public void setUp() {
    testEnv = TestWorkflowEnvironment.newInstance();
    workflowClient = testEnv.getWorkflowClient();
    sample2Workflow = workflowClient.newWorkflowStub(Sample2Workflow.class, WorkflowOptions.newBuilder()
            .setWorkflowExecutionTimeout(Duration.ofSeconds(5))
            .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(1).build())
            .setTaskQueue(TASK_QUEUE).build());

    serviceAActivity = mock(ServiceAActivity.class, withSettings().withoutAnnotations());
    serviceBActivity = mock(ServiceBActivity.class, withSettings().withoutAnnotations());

    Worker worker = testEnv.newWorker(Constants.TASK_QUEUE);
    worker.registerWorkflowImplementationTypes(Sample2WorkflowImpl.class);
    worker.registerActivitiesImplementations(serviceAActivity, serviceBActivity);

    workflowRequest = WorkflowRequest.builder()
        .id(UUID.randomUUID().toString())
        .rqUID(UUID.randomUUID().toString())
        .clientName("junit")
        .build();

    testEnv.start();

    when(serviceAActivity.invokeDSService(any())).then(i -> {
      ServiceAActivity.DSRequest request = i.getArgument(0, ServiceAActivity.DSRequest.class);
      log.debug("mock(ServiceA) request={}", request);
      return ServiceAActivity.DSResponse.PASS;
    });

    doAnswer(i -> {
      ServiceBActivity.DSRequest request = i.getArgument(0, ServiceBActivity.DSRequest.class);
      log.debug("mock(ServiceB) request={}", request);
      /* Signal method to send response 6 */
      sample2Workflow.response6(false);
      /* Signal method to send response 7 */
      sample2Workflow.response7(ServiceBActivity.DSResponse.APPROVE);
      return null;
    }).when(serviceBActivity).submitDSRequest(any());
  }

  @After
  public void tearDown() {
    testEnv.close();
  }

  /**
   * Service A : Pass Service B : Response 6 (false) -> Response 7 (Approve)
   */
  @Test
  public void successExecuteInOrder()
      throws InterruptedException, ExecutionException, TimeoutException {
    final var responseInOrder = true;
    var f = WorkflowClient.execute(sample2Workflow::execute, workflowRequest, responseInOrder);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("executeInOrder: Success", response.getMessage());
  }

  /**
   * Service A : Pass Service B : Response 6 (false) -> Response 7 (Approve)
   */
  @Test
  public void successExecuteInAnyOrder1()
      throws InterruptedException, ExecutionException, TimeoutException {
    final var responseInOrder = false;
    var f = WorkflowClient.execute(sample2Workflow::execute, workflowRequest, responseInOrder);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("executeInAnyOrder: Success", response.getMessage());
  }

  /**
   * Service A : Pass Service B : Response 7 (Approve) -> Response 6 (false)
   */
  @Test
  public void successExecuteInAnyOrder2()
      throws InterruptedException, ExecutionException, TimeoutException {

    doAnswer(i -> {
      ServiceBActivity.DSRequest request = i.getArgument(0, ServiceBActivity.DSRequest.class);
      log.debug("mock(ServiceB) request={}", request);
      /* Signal method to send response 7 */
      sample2Workflow.response7(ServiceBActivity.DSResponse.APPROVE);
      /* Signal method to send response 6 */
      sample2Workflow.response6(false);
      return null;
    }).when(serviceBActivity).submitDSRequest(any());

    final var responseInOrder = false;
    var f = WorkflowClient.execute(sample2Workflow::execute, workflowRequest, responseInOrder);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("executeInAnyOrder: Success", response.getMessage());
  }

  /**
   * Service A : Pass Service B : Response 7 (Approve)
   */
  @Test
  public void successExecuteInAnyOrder3()
      throws InterruptedException, ExecutionException, TimeoutException {

    doAnswer(i -> {
      ServiceBActivity.DSRequest request = i.getArgument(0, ServiceBActivity.DSRequest.class);
      log.debug("mock(ServiceB) request={}", request);
      /* Signal method to send response 7 */
      sample2Workflow.response7(ServiceBActivity.DSResponse.APPROVE);
      return null;
    }).when(serviceBActivity).submitDSRequest(any());

    final var responseInOrder = false;
    var f = WorkflowClient.execute(sample2Workflow::execute, workflowRequest, responseInOrder);
    var response = f.get(2, TimeUnit.SECONDS);
    assertEquals("executeInAnyOrder: Success", response.getMessage());
  }
}