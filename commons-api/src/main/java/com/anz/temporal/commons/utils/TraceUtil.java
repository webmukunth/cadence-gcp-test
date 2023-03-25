package com.anz.temporal.commons.utils;

import static io.opentracing.tag.Tags.COMPONENT;
import static io.opentracing.tag.Tags.ERROR;
import static io.opentracing.tag.Tags.SPAN_KIND;
import static io.opentracing.tag.Tags.SPAN_KIND_CLIENT;
import static io.temporal.internal.logging.LoggerTag.RUN_ID;
import static io.temporal.internal.logging.LoggerTag.WORKFLOW_ID;

import com.anz.temporal.commons.autoconfigure.TemporalConfigurationProperties;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.activity.Activity;

import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.reporters.RemoteReporter;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.jaegertracing.spi.Sampler;
import io.jaegertracing.thrift.internal.senders.UdpSender;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.Timer.Sample;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import io.temporal.opentracing.OpenTracingOptions;
import io.temporal.opentracing.OpenTracingSpanContextCodec;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.MDC;

@Slf4j
public class TraceUtil {


  // used by java objects managed by temporal workflow
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
    addTag(RUN_ID, exe.getRunId());
    addTag(WORKFLOW_ID, exe.getWorkflowId());
  }

  public void recordDuration(String metricName, LocalDateTime start, LocalDateTime end,
      String... tags) {
    if (registry == null) {
      log.warn("TraceUtil not yet initialized");
      return;
    }

    Timer.builder(metricName)
        .publishPercentiles(0.8, 0.9, 0.95, 0.99)
        .publishPercentileHistogram()
        .tags(tags)
        .register(registry)
        .record(Duration.between(start, end));
  }

  public TracerAndTimer getTracerAndTimer(String spanName, String... tags) {
    return new TracerAndTimer(tracer, registry, spanName, tags);
  }

  public <T> T traceAndMeasureActivity(Callable<T> callable) {
    var exceptionClass = "none";
    final var act = Activity.getExecutionContext();

    final var tracerAndTimer = this.getTracerAndTimer(act.getInfo().getActivityType(),
        COMPONENT.getKey(), "activity", SPAN_KIND.getKey(), SPAN_KIND_CLIENT);

    log.debug("About to execute activity {}", act.getInfo().getActivityType());

    try {
      return callable.call();
    } catch (Exception ex) {
      log.error("Exception occurred", ex);
      exceptionClass = ex.getClass().getSimpleName();
      tracerAndTimer.logError(ex);
      throw Activity.wrap(ex);
    } finally {
      tracerAndTimer.close("app_activity",
          "activityType", act.getInfo().getActivityType(),
          "activityId", act.getInfo().getActivityId(),
          "workflowId", act.getInfo().getWorkflowId(),
          "exception", exceptionClass);
    }
  }

  public static OpenTracingOptions getJaegerOpenTracingOptions(TemporalConfigurationProperties prop) {
    try {
      // Using Udp Sender for OpenTracing, make sure to change host and port
      // to your Jaeger options (if using different than in sample)
      RemoteReporter reporter =
              new RemoteReporter.Builder().withSender(new UdpSender(prop.getHost(), prop.getPort(), 0)).build();
      Sampler sampler = new ConstSampler(true);
      Tracer tracer =
              new JaegerTracer.Builder("temporal-sample-opentracing")
                      .withReporter(reporter)
                      .withSampler(sampler)
                      .build();

      return getOpenTracingOptionsForTracer(tracer);
    } catch (Exception e) {
      log.info("Exception configuring Jaeger Tracer: " + e.getMessage());
      return null;
    }
  }

  private static OpenTracingOptions getOpenTracingOptionsForTracer(Tracer tracer) {
    OpenTracingOptions options =
            OpenTracingOptions.newBuilder()
                    .setSpanContextCodec(OpenTracingSpanContextCodec.TEXT_MAP_CODEC)
                    .setTracer(tracer)
                    .build();
    return options;
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