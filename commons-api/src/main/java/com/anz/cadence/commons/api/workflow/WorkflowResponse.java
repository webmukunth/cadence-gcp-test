package com.anz.cadence.commons.api.workflow;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.Wither;

@Value
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class WorkflowResponse {

  @NonNull
  String id;
  @NonNull
  String rqUID;
  @NonNull
  WorkflowStatus workflowStatus;
  @NonNull
  String message;
  @NonFinal
  private Optional<List<ValidationError>> validationErrors;

  public static WorkflowResponseBuilder customBuilder(WorkflowRequest request, WorkflowStatus status) {
    return WorkflowResponse.builder()
        .workflowStatus(status)
        .id(request.getId())
        .rqUID(request.getRqUID());
  }

  public static WorkflowResponseBuilder customBuilder(WorkflowRequest request) {
    return WorkflowResponse.builder()
        .id(request.getId())
        .rqUID(request.getRqUID());
  }
}