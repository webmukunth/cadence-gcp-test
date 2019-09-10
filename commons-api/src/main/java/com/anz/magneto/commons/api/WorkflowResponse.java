package com.anz.magneto.commons.api;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
@Builder(toBuilder = true)
public class WorkflowResponse {

  final private Status status;
  final private String message;

  final public boolean isError() {
    return status == Status.ERROR;
  }

  final public boolean isSuccess() {
    return status == Status.SUCCESS;
  }
}