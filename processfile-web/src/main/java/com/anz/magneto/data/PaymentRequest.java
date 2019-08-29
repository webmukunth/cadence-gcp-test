package com.anz.magneto.data;

import com.anz.magneto.model.payment.ComAnzPmtAddRqType;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PaymentRequest {
  @Id
  final String id;
  @NotNull
  final ComAnzPmtAddRqType pmtAddRqType;
}
