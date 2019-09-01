package com.anz.magneto.data;

import com.anz.magneto.model.payment.ComAnzPmtAddRqType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class PaymentRequest {
  @Id
  final String id;
  final ComAnzPmtAddRqType pmtAddRqType;
}
