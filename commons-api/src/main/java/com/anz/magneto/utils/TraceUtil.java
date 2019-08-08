package com.anz.magneto.utils;

import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.activity.Activity;
import com.uber.cadence.activity.ActivityTask;
import com.uber.cadence.internal.logging.LoggerTag;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
public class TraceUtil {

  private final Tracer tracer;
  private final MeterRegistry registry;

  public TraceUtil(Tracer tracer, MeterRegistry registry) {
    this.tracer = tracer;
    this.registry = registry;
  }

  private static void onError(Throwable throwable, Span span) {
    Tags.ERROR.set(span, Boolean.TRUE);
    if (throwable != null) {
      span.log(errorLogs(throwable));
    }
  }

  private static Map<String, Object> errorLogs(Throwable throwable) {
    Map<String, Object> errorLogs = new HashMap<>(2);
    errorLogs.put("event", Tags.ERROR.getKey());
    errorLogs.put("error.object", throwable);
    return errorLogs;
  }

  private static String standardMetricName(ActivityTask act) {
    return standardMetricName("cadence_" + act.getActivityType());
  }

  public static String standardMetricName(String s) {
    return s.replaceAll("\\p{Punct}++", "_");
  }

  private Span getActiveSpan() {
    return tracer == null ? null : tracer.activeSpan();
  }

  public final void addTag(String name, String val) {
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

  public <T> T traceAndMeasureActivity(Callable<T> callable) {
    /* start timer */
    final var sample = Timer.start(registry);
    var exceptionClass = "none";

    final var act = Activity.getTask();

    /* start tracing */
    final var sb = tracer.buildSpan(act.getActivityType())
        .withTag(Tags.COMPONENT, "activity")
        .withTag(Tags.SPAN_KIND, Tags.SPAN_KIND_CLIENT);

    /* Copy the MDC header key,value to span */
    MDC.getCopyOfContextMap().forEach(sb::withTag);

    final var span = sb.start();

    try (Scope ignored = tracer.activateSpan(span)) {
      return callable.call();
    } catch (Exception ex) {
      log.error("Exception occurred", ex);
      exceptionClass = ex.getClass().getSimpleName();
      onError(ex, span);
      throw Activity.wrap(ex);
    } finally {
      sample.stop(Timer.builder(standardMetricName(act))
          .tags("taskList", act.getTaskList())
          .tags("domain", act.getWorkflowDomain())
          .tags("exception", exceptionClass)
          .register(registry));
      span.finish();
    }
  }
}