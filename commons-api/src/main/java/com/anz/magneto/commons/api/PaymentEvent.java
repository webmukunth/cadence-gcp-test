package com.anz.magneto.commons.api;

import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;
import org.joda.time.DateTime;

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
  DateTime dateTime = new DateTime();

  public static PaymentEvent newInstance(EventType ev, WorkflowRequest request) {
    return PaymentEvent.builder()
        .eventType(ev)
        .id(request.getId())
        .clientName(request.getClientName())
        .rqUID(request.getRqUID())
        .build();
  }
}
