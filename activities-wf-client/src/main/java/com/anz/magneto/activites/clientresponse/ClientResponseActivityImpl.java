package com.anz.magneto.activites.clientresponse;

import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import com.anz.magneto.commons.api.workflow.WorkflowResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientResponseActivityImpl implements ClientResponseActivity {

  @Override
  public void sendResponse(@NonNull WorkflowRequest request, @NonNull WorkflowResponse response) {
    log.info("sendResponse: request={}, response={}", request, response);
  }
}
