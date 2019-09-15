package com.anz.magneto.activities.accounting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
public class AccountingResponse {

  @NonNull AccountingStatus status;
  @NonNull String accountingId;
  @NonNull String paymentId;
  @NonNull String rqUID;

}
