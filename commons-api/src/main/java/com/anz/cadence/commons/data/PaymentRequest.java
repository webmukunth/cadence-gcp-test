package com.anz.cadence.commons.data;

import com.anz.cadence.commons.model.payment.ComAnzPmtAddRqType;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Value
@Wither
@Builder
@NoArgsConstructor
public class PaymentRequest {

  @Id
  @NonFinal
  @NonNull
  @Setter
  private String id;

  @NonFinal
  @NonNull
  @Setter
  private ComAnzPmtAddRqType pmtAddRqType;
}
