package com.anz.cadence.workflow.sample2;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.anz.cadence.activities.sample2.ServiceAActivity;
import com.anz.cadence.activities.sample2.ServiceBActivity;
import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.uber.cadence.client.ActivityCompletionClient;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.testing.TestWorkflowEnvironment;
import com.uber.cadence.worker.Worker;
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
  private ActivityCompletionClient completionClient;

  private Sample2Workflow sample2Workflow;
  private ServiceAActivity serviceAActivity;
  private ServiceBActivity serviceBActivity;
  private WorkflowRequest workflowRequest;


  @Before
  public void setUp() {
    testEnv = TestWorkflowEnvironment.newInstance();
    workflowClient = testEnv.newWorkflowClient();
    completionClient = workflowClient.newActivityCompletionClient();
    sample2Workflow = workflowClient.newWorkflowStub(Sample2Workflow.class);

    serviceAActivity = mock(ServiceAActivity.class);
    serviceBActivity = mock(ServiceBActivity.class);

    Worker worker = testEnv.newWorker(Constants.TASK_LIST);
    worker.registerWorkflowImplementationTypes(Sample2WorkflowImpl.class);
    worker.registerActivitiesImplementations(serviceAActivity, serviceBActivity);

    workflowRequest = WorkflowRequest.builder()
        .id(UUID.randomUUID().toString())
        .rqUID(UUID.randomUUID().toString())
        .clientName("junit")
        .build();

    testEnv.start();

    when(serviceAActivity.invokeDSService(any())).then(i -> {
      ServiceAActivity.DSRequest request = i.getArgumentAt(0, ServiceAActivity.DSRequest.class);
      log.debug("mock(ServiceA) request={}", request);
      return ServiceAActivity.DSResponse.PASS;
    });

    doAnswer(i -> {
      ServiceBActivity.DSRequest request = i.getArgumentAt(0, ServiceBActivity.DSRequest.class);
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
   * Service A : Pass
   * Service B : Response 6 (false) -> Response 7 (Approve)
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
   * Service A : Pass
   * Service B : Response 6 (false) -> Response 7 (Approve)
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
   * Service A : Pass
   * Service B : Response 7 (Approve) -> Response 6 (false)
   */
  @Test
  public void successExecuteInAnyOrder2()
      throws InterruptedException, ExecutionException, TimeoutException {

    doAnswer(i -> {
      ServiceBActivity.DSRequest request = i.getArgumentAt(0, ServiceBActivity.DSRequest.class);
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
   * Service A : Pass
   * Service B : Response 7 (Approve)
   */
  @Test
  public void successExecuteInAnyOrder3()
      throws InterruptedException, ExecutionException, TimeoutException {

    doAnswer(i -> {
      ServiceBActivity.DSRequest request = i.getArgumentAt(0, ServiceBActivity.DSRequest.class);
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