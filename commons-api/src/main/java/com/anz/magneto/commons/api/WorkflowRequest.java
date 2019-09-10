package com.anz.magneto.commons.api;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
@Builder(toBuilder = true)
public class WorkflowRequest {

  final private String requestId;
}
