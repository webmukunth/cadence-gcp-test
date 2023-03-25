package com.anz.temporal.commons.data;

import com.anz.temporal.commons.model.payment.ComAnzPmtAddRqType;
import java.time.LocalDateTime;

import com.anz.temporal.commons.Constants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Wither
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentRequest {

  @Id
  @NonNull
  String id;

  @NonNull
  ComAnzPmtAddRqType pmtAddRqType;

  @NonNull
  @Default
  LocalDateTime submitDateTime = LocalDateTime.now(Constants.UTC);
}
