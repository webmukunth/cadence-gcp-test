package com.anz.cadence.commons.data;

import com.anz.cadence.commons.model.payment.ComAnzPmtAddRqType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PaymentRequest {

  @Id
  @NonNull
  final private String id;
  @NonNull
  final private ComAnzPmtAddRqType pmtAddRqType;
}
