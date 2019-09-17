package com.anz.cadence.commons.api.workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
@Builder
@AllArgsConstructor
public class ValidationError {

  @NonNull String code;
  @NonNull String message;
}
