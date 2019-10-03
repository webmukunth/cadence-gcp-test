package com.anz.cadence.commons.api.workflow;

import static com.anz.cadence.commons.Constants.UTC;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
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
