package com.anz.cadence.commons.data;

import com.anz.cadence.commons.model.payment.ComAnzPmtAddRqType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.NonFinal;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

  @Id
  @NonNull
  private String id;

  @NonNull
  private ComAnzPmtAddRqType pmtAddRqType;
}
