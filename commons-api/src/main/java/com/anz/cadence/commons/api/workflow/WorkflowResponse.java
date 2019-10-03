package com.anz.cadence.commons.api.workflow;

import static com.anz.cadence.commons.Constants.UTC;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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

  @NonNull String id;
  @NonNull String rqUID;
  @NonNull WorkflowStatus workflowStatus;
  @NonNull String message;
  @NonNull LocalDateTime startDateTime;
  @Default
  @NonNull LocalDateTime responseDateTime = LocalDateTime.now(UTC);
  @NonFinal
  Optional<List<ValidationError>> validationErrors;

  public static WorkflowResponseBuilder customBuilder(WorkflowRequest request,
      WorkflowStatus status) {
    return WorkflowResponse.builder()
        .workflowStatus(status)
        .id(request.getId())
        .startDateTime(request.getRequestDateTime())
        .rqUID(request.getRqUID());
  }

  public static WorkflowResponseBuilder customBuilder(WorkflowRequest request) {
    return WorkflowResponse.builder()
        .id(request.getId())
        .startDateTime(request.getRequestDateTime())
        .rqUID(request.getRqUID());
  }
}