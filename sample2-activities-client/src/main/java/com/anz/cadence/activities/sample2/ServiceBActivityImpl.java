package com.anz.cadence.activities.sample2;

import com.anz.cadence.commons.utils.TraceUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServiceBActivityImpl implements ServiceBActivity {

  final private TraceUtil traceUtil;

  public ServiceBActivityImpl(TraceUtil traceUtil) {
    this.traceUtil = traceUtil;
  }

  @Override
  public void submitDSRequest(@NonNull DSRequest request) {
    traceUtil.traceAndMeasureActivity(() -> {
      log.info("Got a request to serve {}", request);
      return null;
    });
  }
}
