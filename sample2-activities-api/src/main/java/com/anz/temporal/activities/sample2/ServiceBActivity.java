package com.anz.temporal.activities.sample2;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

@ActivityInterface
public interface ServiceBActivity {

  @ActivityMethod
  void submitDSRequest(@NonNull DSRequest request);

  enum DSResponse {
    APPROVE,
    DECLINE
  }

  @Value
  @Wither
  @Builder(toBuilder = true)
  class DSRequest {

    @NonNull String rqUID;
    @NonNull String id;
  }
}
