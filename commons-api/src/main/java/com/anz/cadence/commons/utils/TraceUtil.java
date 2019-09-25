package com.anz.cadence.commons.utils;

import static io.opentracing.tag.Tags.COMPONENT;
import static io.opentracing.tag.Tags.ERROR;
import static io.opentracing.tag.Tags.SPAN_KIND;
import static io.opentracing.tag.Tags.SPAN_KIND_CLIENT;

import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.activity.Activity;
import com.uber.cadence.activity.ActivityTask;
import com.uber.cadence.internal.logging.LoggerTag;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.Timer.Sample;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@Slf4j
public class TraceUtil {


  // used by java objects managed by cadence workflow
  private static TraceUtil globalTraceUtil = new TraceUtil();

  private final Tracer tracer;
  private final MeterRegistry registry;

  private TraceUtil() {
    tracer = null;
    registry = null;
  }

  public TraceUtil(Tracer tracer, MeterRegistry registry) {
    this.tracer = tracer;
    this.registry = registry;
  }

  private static String standardMetricName(ActivityTask act) {
    return standardMetricName("cadence_" + act.getActivityType());
  }

  public static String standardMetricName(String s) {
    return s.replaceAll("\\p{Punct}++", "_");
  }

  public static TraceUtil getGlobalTraceUtil() {
    return globalTraceUtil;
  }

  public static void setGlobalTraceUtil(TraceUtil tracerUtil) {
    globalTraceUtil = tracerUtil;
  }

  private Span getActiveSpan() {
    return tracer == null ? null : tracer.activeSpan();
  }

  final void addTag(String name, String val) {
    final var span = getActiveSpan();
    if (span == null || val == null) {
      log.debug("Span '{}' or value '{}' is null for {}", span, val, name);
      return;
    }
    span.setTag(name, val);
  }

  public void addWorkflowExecutionTag(WorkflowExecution exe) {
    if (exe == null) {
      return;
    }
    addTag(LoggerTag.RUN_ID, exe.getRunId());
    addTag(LoggerTag.WORKFLOW_ID, exe.getWorkflowId());
  }

  public TracerAndTimer getTracerAndTimer(String spanName, String... tags) {
    return new TracerAndTimer(tracer, registry, spanName, tags);
  }

  public <T> T traceAndMeasureActivity(Callable<T> callable) {
    var exceptionClass = "none";
    final var act = Activity.getTask();

    final var tracerAndTimer = this.getTracerAndTimer(act.getActivityType(),
        COMPONENT.getKey(), "activity", SPAN_KIND.getKey(), SPAN_KIND_CLIENT);

    try {
      return callable.call();
    } catch (Exception ex) {
      log.error("Exception occurred", ex);
      exceptionClass = ex.getClass().getSimpleName();
      tracerAndTimer.logError(ex);
      throw Activity.wrap(ex);
    } finally {
      tracerAndTimer.close( "app_activity",
          "activityType", act.getActivityType(),
          "taskList", act.getTaskList(),
          "domain", act.getWorkflowDomain(),
          "exception", exceptionClass);
    }
  }

  @Slf4j
  public static class TracerAndTimer {

    final private MeterRegistry registry;
    final private Sample sample;
    final private Span span;
    final private Scope scope;

    public TracerAndTimer(Tracer tracer, MeterRegistry registry, String spanName,
        String... spanTags) {
      this.registry = registry;

      /* Start the clock */
      this.sample = Timer.start(registry);

      /* Start tracing */
      final var spanBuilder = tracer.buildSpan(spanName);
      if (spanTags != null) {
        for (int i = 0; i < spanTags.length / 2; i += 2) {
          spanBuilder.withTag(spanTags[i], spanTags[i + 1]);
        }
      }
      MDC.getCopyOfContextMap().forEach(spanBuilder::withTag);
      span = spanBuilder.start();
      scope = tracer.activateSpan(span);
    }

    private static Map<String, Object> errorLogs(Throwable throwable) {
      Map<String, Object> errorLogs = new HashMap<>(2);
      errorLogs.put("event", ERROR.getKey());
      errorLogs.put("error.object", throwable);
      return errorLogs;
    }

    public void close(String metricName, String... tags) {
      sample.stop(Timer.builder(metricName)
          .publishPercentiles(0.8, 0.9, 0.95, 0.99)
          .publishPercentileHistogram()
          .tags(tags)
          .register(registry));
      scope.close();
      span.finish();
    }

    public void logError(Throwable throwable) {
      ERROR.set(span, Boolean.TRUE);
      if (throwable != null) {
        span.log(errorLogs(throwable));
      }
    }
  }
}