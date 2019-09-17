package com.anz.cadence.commons.api.workflow;

import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
@Builder
public class ValidationErrors {

  @Singular
  List<@NonNull ValidationError> errors;
}
