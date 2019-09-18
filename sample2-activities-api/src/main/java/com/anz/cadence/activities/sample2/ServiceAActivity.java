package com.anz.cadence.activities.sample2;

import com.anz.cadence.commons.Constants;
import com.uber.cadence.activity.ActivityMethod;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

public interface ServiceAActivity {

  @ActivityMethod(taskList = Constants.TASK_LIST, scheduleToCloseTimeoutSeconds = 5)
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
