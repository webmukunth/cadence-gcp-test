package com.anz.magneto.commons.kafka;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Slf4j
public class PaymentEvent {

  @NonNull
  final private EventType eventType;
  @NonNull
  final private String client;
  @NonNull
  final private String id;
  @NonNull
  final private DateTime dateTime;

  enum EventType {
    RECEIVED,
    ACCEPTED
  }
}
