package com.anz.cadence.commons.api;

import com.anz.cadence.commons.api.workflow.WorkflowRequest;
import java.time.LocalTime;
import java.time.ZoneId;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
@Builder(toBuilder = true)
public class PaymentEvent {

  @NonNull EventType eventType;
  @NonNull String id;
  @NonNull String rqUID;
  @NonNull String clientName;
  @NonNull
  @Builder.Default
  LocalTime dateTime = LocalTime.now(ZoneId.of("GMT"));

  public static PaymentEvent newInstance(EventType ev, WorkflowRequest request) {
    return PaymentEvent.builder()
        .eventType(ev)
        .id(request.getId())
        .clientName(request.getClientName())
        .rqUID(request.getRqUID())
        .build();
  }
}
