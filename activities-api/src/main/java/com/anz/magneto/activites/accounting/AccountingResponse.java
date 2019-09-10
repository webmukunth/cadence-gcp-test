package com.anz.magneto.activites.accounting;

import com.anz.magneto.commons.api.Status;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
@Builder(toBuilder = true)
public class AccountingResponse {

  final private Status status;
  final private String accountingId;

  final public boolean isError() {
    return status == Status.ERROR;
  }

  final public boolean isSuccess() {
    return status == Status.SUCCESS;
  }
}
