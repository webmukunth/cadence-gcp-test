package com.anz.temporal.web.sample2.service;

import com.anz.temporal.commons.Constants;
import com.anz.temporal.commons.api.workflow.LimitType;
import com.anz.temporal.commons.api.workflow.WorkflowRequest;
import com.anz.temporal.commons.model.payment.ComAnzPmtAddRqType;
import com.anz.temporal.commons.utils.TraceUtil;
import com.anz.temporal.workflow.sample2.Sample2Workflow;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
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
        WorkflowOptions.newBuilder()
            .setTaskQueue(Constants.TASK_QUEUE)
            .setWorkflowId(id)
            .setWorkflowExecutionTimeout(Duration.ofHours(4))
            .build()
    );
    return WorkflowClient.start(wfInstance::execute, workflowRequest, executeInOrder);
  }
}
