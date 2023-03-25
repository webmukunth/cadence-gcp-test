package com.anz.temporal.commons.api.workflow;

import static com.anz.temporal.commons.Constants.UTC;

import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.Wither;

@Value
@Wither
@Builder(toBuilder = true)
public class WorkflowRequest {

  @NonNull
  String id;
  @NonNull
  String rqUID;
  @NonNull
  String clientName;
  @NonNull
  @Builder.Default
  LimitType limitType = LimitType.AFPONLY;
  @NonNull
  @Builder.Default
  LocalDateTime requestDateTime = LocalDateTime.now(UTC);
}
