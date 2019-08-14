package com.anz.magneto.service.download;

import com.anz.magneto.api.download.DownloadActivity;
import com.anz.magneto.api.download.DownloadActivity.TaskListFileNamePair;
import com.anz.magneto.api.download.FileProcessingWorkflow;
import com.anz.magneto.api.download.ProcessFileActivity;
import com.anz.magneto.utils.TraceUtil;
import com.uber.cadence.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileProcessingWorkflowImpl implements FileProcessingWorkflow {

  private final DownloadActivity downloadActivity;
  private final ProcessFileActivity processFileActivity;

  public FileProcessingWorkflowImpl() {
    downloadActivity = Workflow.newActivityStub(DownloadActivity.class);
    processFileActivity = Workflow.newActivityStub(ProcessFileActivity.class);
    log.debug("Created new instance with downloadActivity: {}, processFileActivity: {}",
        downloadActivity, processFileActivity);
  }

  @Override
  public void processFile(String fileName) {
    TraceUtil.getGlobalTraceUtil().traceAndMeasureWorkflow(() -> {
      log.info("fileProcessWorkflow: About process file {}", fileName);
      TaskListFileNamePair taskListFileNamePair = downloadActivity.download(fileName);
      log.info("fileProcessWorkflow: taskListFileNamePair: {}", taskListFileNamePair);
      processFileActivity.process(taskListFileNamePair.getFilePath());
      log.info("fileProcessWorkflow: Done {}", fileName);
      return null;
    });
  }
}
