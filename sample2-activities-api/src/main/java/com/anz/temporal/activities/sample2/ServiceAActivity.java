package com.anz.temporal.activities.sample2;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

@ActivityInterface
public interface ServiceAActivity {

  @ActivityMethod
  @NonNull DSResponse invokeDSService(@NonNull DSRequest request);

  enum DSResponse {
    PASS,
    FAIL
  }

  @Value
  @Wither
  @Builder(toBuilder = true)
  class DSRequest {

    @NonNull
    String rqUID;
    @NonNull
    String id;
  }
}
