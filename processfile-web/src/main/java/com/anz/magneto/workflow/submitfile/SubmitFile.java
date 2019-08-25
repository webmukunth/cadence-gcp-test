package com.anz.magneto.workflow.submitfile;

import com.anz.magneto.api.download.FileProcessingWorkflow;
import com.anz.magneto.commons.Constants;
import com.anz.magneto.model.payment.ComAnzPmtAddRqType;
import com.anz.magneto.utils.TraceUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import io.micrometer.core.annotation.Timed;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@Slf4j
public class SubmitFile {

  private WorkflowClient wfClient;
  private TraceUtil traceUtil;
  private XmlMapper xmlMapper;

  @Autowired
  public SubmitFile(WorkflowClient wfClient, TraceUtil traceUtil, XmlMapper xmlMapper) {
    this.wfClient = wfClient;
    this.traceUtil = traceUtil;
    this.xmlMapper = xmlMapper;
    log.info("workflowClient: {}", wfClient);
  }

  @RequestMapping("/submitfile/{filename}")
  @Timed(description = "Submit file for processing")
  String submitFile(@PathVariable("filename") String fileName) {
    traceUtil.addTag("filename", fileName);

    log.info("Got a request to submit file : {}", fileName);

    FileProcessingWorkflow wfInstance = wfClient.newWorkflowStub(
        FileProcessingWorkflow.class,
        new WorkflowOptions.Builder()
            .setTaskList(Constants.TASK_LIST)
            .build()
    );

    final var ret = WorkflowClient.start(wfInstance::processFile, fileName);
    traceUtil.addWorkflowExecutionTag(ret);
    log.info("Submitted file for proessing: {}", ret);
    return fileName + ":" + ret.toString() + "\n";
  }

  @PostMapping(
      path = "/transform",
      consumes = "application/vnd.gpa.v1+xml",
      produces = "application/vnd.gpa.v1+json"
  )
  @Timed(description = "Transform request")
  @JsonInclude(Include.NON_NULL)
  ComAnzPmtAddRqType transform(InputStream inputStream) throws IOException {
    log.info("Got a request to transform");
    final var ret = xmlMapper.readValue(inputStream, ComAnzPmtAddRqType.class);
    log.info("Response: {}", ret);
    return ret;
  }

  @PostMapping(
      path = "/transform2",
      consumes = {"application/vnd.gpa.v1+xml", "application/vnd.gpa.v1+json"},
      produces = "application/vnd.gpa.v1+json"
  )
  @Timed(description = "Transform request")
  @JsonInclude(Include.NON_NULL)
  ComAnzPmtAddRqType transform2(@RequestBody ComAnzPmtAddRqType request) throws IOException {
    log.info("Request: {}", request);
    return request;
  }

}
