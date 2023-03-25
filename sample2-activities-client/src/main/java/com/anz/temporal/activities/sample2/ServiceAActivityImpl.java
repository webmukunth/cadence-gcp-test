package com.anz.temporal.activities.sample2;

import com.anz.temporal.commons.utils.TraceUtil;
import io.temporal.activity.Activity;
import io.temporal.client.ActivityCompletionClient;
import java.util.concurrent.ForkJoinPool;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServiceAActivityImpl implements ServiceAActivity {

  final private TraceUtil traceUtil;
  final private ActivityCompletionClient completionClient;

  @Autowired
  public ServiceAActivityImpl(TraceUtil traceUtil, ActivityCompletionClient completionClient) {
    this.traceUtil = traceUtil;
    this.completionClient = completionClient;
  }

  @Override
  public @NonNull DSResponse invokeDSService(@NonNull DSRequest request) {
    return traceUtil.traceAndMeasureActivity(() -> {
      log.info("Got a request to serve {}", request);

      /* To make this as asynchronous activity, don't send any response to temporal */
      Activity.getExecutionContext().doNotCompleteOnReturn();

      /* Simulate response from DS */
      byte[] taskToken = Activity.getExecutionContext().getTaskToken();
      ForkJoinPool.commonPool().execute(() -> {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        log.info("About to send response for request {}", request);
        completionClient.complete(taskToken, DSResponse.PASS);
      });

      /* This return is ignored by temporal */
      return null;
    });
  }
}
