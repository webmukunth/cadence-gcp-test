package com.anz.magneto.service.download;

import com.anz.magneto.api.download.ProcessFileActivity;
import com.anz.magneto.commons.utils.TraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProcessFileActivityImpl implements ProcessFileActivity {

  private final TraceUtil traceUtil;

  public ProcessFileActivityImpl(TraceUtil traceUtil) {
    this.traceUtil = traceUtil;
  }

  @Override
  public void process(String filePath) {
    traceUtil.traceAndMeasureActivity(() -> {
      log.info("processFileActivity: {}", filePath);
      return null;
    });
  }
}
