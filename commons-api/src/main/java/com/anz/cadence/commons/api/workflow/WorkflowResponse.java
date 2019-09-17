package com.anz.cadence.commons.api.workflow;

import java.util.List;
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
  final private WorkflowStatus workflowStatus;
  @NonNull
  final private String message;
  @NonFinal
  private List<ValidationError> validationErrors;
}