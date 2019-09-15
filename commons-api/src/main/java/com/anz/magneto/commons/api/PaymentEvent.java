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

  String id;
  EventType eventType;
  String client;
  @Builder.Default
  DateTime dateTime = new DateTime();
}
