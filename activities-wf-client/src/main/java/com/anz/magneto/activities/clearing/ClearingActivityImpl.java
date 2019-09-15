package com.anz.magneto.activities.clearing;

import com.anz.magneto.commons.api.workflow.WorkflowRequest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClearingActivityImpl implements ClearingActivity {

  @Override
  public @NonNull ClearingResponse clearPayment(@NonNull WorkflowRequest request) {
    log.info("clearPayment: request={}", request);
    return ClearingResponse.builder().clearingId("123").status(ClearingStatus.CLEARED).build();
  }
}
