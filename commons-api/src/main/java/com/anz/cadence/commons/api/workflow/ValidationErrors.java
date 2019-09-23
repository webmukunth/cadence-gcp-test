package com.anz.cadence.commons.api.workflow;

import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
@Builder
public class ValidationErrors {

  @Singular
  List<ValidationError> errors;

  public Optional<List<ValidationError>> getOptionalErrors() {
    return Optional.ofNullable(this.getErrors());
  }
}
