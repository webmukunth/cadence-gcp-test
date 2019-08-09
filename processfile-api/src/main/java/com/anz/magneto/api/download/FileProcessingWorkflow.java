package com.anz.magneto.api.download;

import com.anz.magneto.commons.Constants;
import com.uber.cadence.workflow.WorkflowMethod;

public interface FileProcessingWorkflow {

  @WorkflowMethod(taskList = Constants.TASK_LIST,
      executionStartToCloseTimeoutSeconds = 601,
      taskStartToCloseTimeoutSeconds = 20
  )
  void processFile(String fileName);
}
