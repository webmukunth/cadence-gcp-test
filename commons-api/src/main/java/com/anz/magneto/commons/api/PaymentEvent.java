package com.anz.magneto.commons.api;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;
import org.joda.time.DateTime;

@Value
@Wither
@Builder(toBuilder = true)
public class PaymentEvent {

  @NonNull
  final private EventType eventType;
  @NonNull
  final private String client;
  @NonNull
  final private String id;
  @Builder.Default
  final private DateTime dateTime = new DateTime();
}
