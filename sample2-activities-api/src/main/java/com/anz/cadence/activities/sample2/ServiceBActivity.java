package com.anz.cadence.activities.samplepayment;

import com.anz.cadence.commons.Constants;
import com.uber.cadence.activity.ActivityMethod;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

public interface ServiceBActivity {

  @ActivityMethod(taskList = Constants.TASK_LIST, scheduleToCloseTimeoutSeconds = 5)
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
