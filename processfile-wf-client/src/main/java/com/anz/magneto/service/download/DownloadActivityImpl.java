package com.anz.magneto.service.download;

import com.anz.magneto.api.download.DownloadActivity;
import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.utils.TraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DownloadActivityImpl implements DownloadActivity {

  private final TraceUtil traceUtil;

  public DownloadActivityImpl(TraceUtil traceUtil) {
    this.traceUtil = traceUtil;
  }

  @Override
  public TaskListFileNamePair download(String fileName) {
    return traceUtil.traceAndMeasureActivity(() -> {
      final var ret = new TaskListFileNamePair(Constants.TASK_LIST, fileName);
      log.info("downloadActivity: {}", ret);
      return ret;
    });
  }
}

