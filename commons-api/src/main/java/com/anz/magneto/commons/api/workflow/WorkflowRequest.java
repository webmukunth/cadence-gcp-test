package com.anz.magneto.commons.api.workflow;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
@Builder(toBuilder = true)
public class WorkflowRequest {

  @NonNull
  String requestId;

  @NonNull
  @Builder.Default
  LimitType limitType = LimitType.AFPONLY;

}
