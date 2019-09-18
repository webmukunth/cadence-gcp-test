package com.anz.cadence.workflow.sample2;

import com.anz.cadence.activities.sample2.ServiceAActivity;
import com.anz.cadence.activities.sample2.ServiceBActivity;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.anz.cadence.commons.api.workflow.WorkflowResponse;
import com.anz.cadence.commons.api.workflow.WorkflowStatus;
import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Workflow;
import java.time.Duration;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sample2WorkflowImpl implements Sample2Workflow {

  final private ServiceAActivity serviceAActivity;
  final private ServiceBActivity serviceBActivity;

  private Boolean quarantine = null;
  private ServiceBActivity.DSResponse dsResponse = null;

  public Sample2WorkflowImpl() {
    serviceAActivity = Workflow.newActivityStub(ServiceAActivity.class,
        new ActivityOptions.Builder().setScheduleToCloseTimeout(Duration.ofSeconds(10)).build());
    serviceBActivity = Workflow.newActivityStub(ServiceBActivity.class,
        new ActivityOptions.Builder().setScheduleToCloseTimeout(Duration.ofSeconds(10)).build());
  }

  @Override
  public WorkflowResponse execute(@NonNull WorkflowRequest request, boolean responseInOrder) {
    log.debug("execute: request={}", request);
    /* Invoke Service A  Asynchronously and wait for response. Cadence does the magic of
     * converting async as sync call */
    final var aResponse = serviceAActivity.invokeDSService(
        ServiceAActivity.DSRequest.builder()
            .id(UUID.randomUUID().toString())
            .rqUID(request.getRqUID())
            .build()
    );

    if (aResponse == ServiceAActivity.DSResponse.FAIL) {
      log.debug("execute: Service A Failed");
      return WorkflowResponse.builder()
          .message("Service A Failed")
          .workflowStatus(WorkflowStatus.STOPPED)
          .build();
    }

    log.debug("execute: Service A Passed");

    /* Submit the request to service B */
    serviceBActivity.submitDSRequest(
        ServiceBActivity.DSRequest.builder()
            .id(UUID.randomUUID().toString())
            .rqUID(request.getRqUID())
            .build()
    );

    if (responseInOrder) {
      return responseInOrder(request);
    } else {
      return responseInAnyOrder(request);
    }
  }

  private WorkflowResponse responseInOrder(@NonNull WorkflowRequest request) {
    /* This sample waits for response 6 and 7 in order */

    if (!Workflow.await(Duration.ofMinutes(3), () -> quarantine != null)) {
      /* Timeout. No response is considered as don't quarantine */
      log.debug("executeInOrder: Response 6 timed out");
      response6(false);
    }

    /* Default timeout for response 7 is 3 minutes, when quarantined wait for 4 hours */
    Duration response7Timeout = Duration.ofMinutes(3);
    if (quarantine) {
      log.debug("executeInOrder: Need to quarantine the txn for 4 hours");
      response7Timeout = Duration.ofHours(4);
    }

    if (!Workflow.await(response7Timeout, () -> dsResponse != null)) {
      /* Timeout. No response is considered as approved */
      log.debug("executeInOrder: Response 7 timed out");
      response7(ServiceBActivity.DSResponse.APPROVE);
    }

    if (dsResponse == ServiceBActivity.DSResponse.DECLINE) {
      log.debug("executeInOrder: Service B Failed");
      return WorkflowResponse.builder()
          .message("executeInOrder: Service B Declined")
          .workflowStatus(WorkflowStatus.STOPPED)
          .build();
    }

    return WorkflowResponse.builder()
        .message("executeInOrder: Success")
        .workflowStatus(WorkflowStatus.SUCCESS)
        .build();
  }

  private WorkflowResponse responseInAnyOrder(@NonNull WorkflowRequest request) {
    /* This sample waits for any one of response 6 or 7 to come */
    if (!Workflow.await(Duration.ofMinutes(3), () -> (quarantine != null || dsResponse != null))) {
      /* Timeout, no response from service b */
      log.debug("executeInAnyOrder: Service B timed out");
      return WorkflowResponse.builder()
          .message("Service B Timedout")
          .workflowStatus(WorkflowStatus.STOPPED)
          .build();
    }

    if (dsResponse == null) {
      /* Got response 6 first */
      /* Default timeout for response 7 is 3 minutes, when quarantined wait for 4 hours */
      Duration response7Timeout = Duration.ofMinutes(3);
      if (quarantine) {
        log.debug("executeInAnyOrder: Need to quarantine the txn for 4 hours");
        response7Timeout = Duration.ofHours(4);
      }

      if (!Workflow.await(response7Timeout, () -> dsResponse != null)) {
        /* Timeout. No response is considered as approved */
        log.debug("executeInAnyOrder: Response 7 timed out");
        response7(ServiceBActivity.DSResponse.APPROVE);
      }
    }

    if (dsResponse == ServiceBActivity.DSResponse.DECLINE) {
      log.debug("executeInAnyOrder: Service B Failed");
      return WorkflowResponse.builder()
          .message("executeInAnyOrder: Service B Declined")
          .workflowStatus(WorkflowStatus.STOPPED)
          .build();
    }

    return WorkflowResponse.builder()
        .message("executeInAnyOrder: Success")
        .workflowStatus(WorkflowStatus.SUCCESS)
        .build();
  }

  @Override
  public void response6(@NonNull Boolean quarantine) {
    log.info("Signal 6: quarantine={}", quarantine);
    this.quarantine = quarantine;
  }

  @Override
  public void response7(@NonNull ServiceBActivity.DSResponse response) {
    log.info("Signal 7: DSResponse={}", response);
    this.dsResponse = response;
  }
}
