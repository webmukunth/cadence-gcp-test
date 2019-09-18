package com.anz.cadence.web.sample2.service;

import com.anz.cadence.commons.Constants;
import com.anz.cadence.commons.api.workflow.LimitType;
import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import com.anz.cadence.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.cadence.commons.utils.TraceUtil;
import com.anz.cadence.workflow.sample2.Sample2Workflow;
import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import java.time.Duration;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Sample2WorkflowClient {

  final private WorkflowClient wfClient;
  final private TraceUtil traceUtil;

  @Autowired
  public Sample2WorkflowClient(WorkflowClient wfClient, TraceUtil traceUtil) {
    this.wfClient = wfClient;
    this.traceUtil = traceUtil;
  }

  public WorkflowExecution executeInOrder(ComAnzPmtAddRqType request) {
    return execute(request, true);
  }

  public WorkflowExecution executeInAnyOrder(ComAnzPmtAddRqType request) {
    return execute(request, false);
  }

  private WorkflowExecution execute(ComAnzPmtAddRqType request, boolean executeInOrder) {
    final var id = UUID.randomUUID().toString();
    final var workflowRequest = WorkflowRequest.builder()
        .id(id)
        .rqUID(request.getRqUID())
        .clientName(request.getMsgHdr().getClientName())
        .limitType(LimitType.valueOf(request.getFromAcct().getPmtAuthMethod()))
        .build();

    log.info("request={}", workflowRequest);

    Sample2Workflow wfInstance = wfClient.newWorkflowStub(Sample2Workflow.class,
        new WorkflowOptions.Builder()
            .setTaskList(Constants.TASK_LIST)
            .setWorkflowId(id)
            .setExecutionStartToCloseTimeout(Duration.ofHours(4))
            .build()
    );

    return WorkflowClient.start(wfInstance::execute, workflowRequest, executeInOrder);
  }
}
