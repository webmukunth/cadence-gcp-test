package com.anz.magneto.activites.clearing;

import com.anz.magneto.activites.accounting.AccountingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
@Builder(toBuilder = true)
@AllArgsConstructor
public class ClearingResponse {

  @NonNull ClearingStatus status;
  @NonNull String clearingId;

}
