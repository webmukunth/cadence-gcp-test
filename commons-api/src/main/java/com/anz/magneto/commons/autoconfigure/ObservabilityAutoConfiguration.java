package com.anz.magneto.commons.autoconfigure;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.commons.utils.TraceUtil;
import com.uber.m3.tally.Buckets;
import com.uber.m3.tally.Capabilities;
import com.uber.m3.tally.RootScopeBuilder;
import com.uber.m3.tally.Scope;
import com.uber.m3.tally.StatsReporter;
import com.uber.m3.util.Duration;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.opentracing.Tracer;
import io.opentracing.contrib.java.spring.jaeger.starter.TracerBuilderCustomizer;
import io.opentracing.contrib.spring.tracer.configuration.TracerAutoConfiguration;
import io.opentracing.tag.Tags;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(TracerAutoConfiguration.class)
@ConditionalOnClass(value = {Tracer.class, MeterRegistry.class})
@Slf4j
public class ObservabilityAutoConfiguration {

  @Value("${spring.application.name:no-name}")
  private String applicationName;

  @Bean
  @ConditionalOnMissingBean
  public TraceUtil traceUtil(Tracer tracer, MeterRegistry registry) {
    var ret = new TraceUtil(tracer, registry);
    TraceUtil.setGlobalTraceUtil(ret);
    log.info("Initialized traceUtil: {}", ret);
    return ret;
  }

  @Bean
  @ConditionalOnMissingBean
  public Scope metricScope(@Autowired MeterRegistry meterRegistry) {
    final var sb = new RootScopeBuilder();
    final var ret = sb.reporter(new PublishToPrometheusReporter(meterRegistry))
        .reportEvery(Duration.ofSeconds(30));
    log.info("Initialized metricScope {}", ret);
    return ret;
  }

  @Bean
  @ConditionalOnMissingBean
  MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry -> registry.config().commonTags(
        Tags.COMPONENT.getKey(), "cadence",
        "application", applicationName,
        "clientDomain", Constants.DOMAIN,
        "clientInstance", Constants.INSTANCE_NAME
    );
  }

  @Bean
  @ConditionalOnMissingBean
  TracerBuilderCustomizer tracerBuilderCustomizer() {
    return builder -> builder.withTags(
        Map.of(
            "application", applicationName,
            "clientDomain", Constants.DOMAIN,
            "clientInstance", Constants.INSTANCE_NAME
        )
    );
  }

  @Slf4j
  static class PublishToPrometheusReporter implements StatsReporter {

    private final MeterRegistry meterRegistry;

    PublishToPrometheusReporter(MeterRegistry meterRegistry) {
      this.meterRegistry = meterRegistry;
    }

    Iterable<Tag> tags(Map<String, String> map) {
      return map.entrySet().stream()
          .map(e -> Tag.of(e.getKey(), e.getValue()))
          .collect(Collectors.toList());
    }

    @Override
    public void reportCounter(String name, Map<String, String> tags, long value) {
      final var metricName = TraceUtil.standardMetricName(name);
      log.trace("reportCounter: name: {} {}, value: {}, tags: {}", name, metricName, value, tags);
      meterRegistry.counter(metricName, tags(tags)).increment(value);
    }

    @Override
    public void reportGauge(String name, Map<String, String> tags, double value) {
      final var metricName = TraceUtil.standardMetricName(name);
      log.debug("reportGauge: name: {} {}, value: {}, tags: {}", name, metricName, value, tags);
      meterRegistry.gauge(metricName, tags(tags), value);
    }

    @Override
    public void reportTimer(String name, Map<String, String> tags, Duration interval) {
      final var metricName = TraceUtil.standardMetricName(name);
      log.trace("reportTimer: name: {} {}, interval: {}, tags: {}", name, metricName, interval,
          tags);
      meterRegistry.timer(metricName, tags(tags)).record(interval.getNanos(), TimeUnit.NANOSECONDS);
    }

    @Override
    public void reportHistogramValueSamples(String name, Map<String, String> tags, Buckets buckets,
        double bucketLowerBound, double bucketUpperBound, long samples) {
      final var metricName = TraceUtil.standardMetricName(name);
      log.info("reportHistogramValueSamples: name: {} {}, buckets: {}, "
              + "bucketLowerBound: {}, bucketUpperBound: {}, samples: {}, tags: {}",
          name, metricName, buckets, bucketLowerBound, bucketUpperBound, samples, tags);
    }

    @Override
    public void reportHistogramDurationSamples(String name, Map<String, String> tags,
        Buckets buckets, Duration bucketLowerBound, Duration bucketUpperBound, long samples) {
      final var metricName = TraceUtil.standardMetricName(name);
      log.info("reportHistogramDurationSamples: name: {} {}, buckets: {}, "
              + "bucketLowerBound: {}, bucketUpperBound: {}, samples: {}, tags: {}",
          name, metricName, buckets, bucketLowerBound, bucketUpperBound, samples, tags);
    }

    @Override
    public Capabilities capabilities() {
      return new Capabilities() {
        @Override
        public boolean reporting() {
          return true;
        }

        @Override
        public boolean tagging() {
          return true;
        }
      };
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }
  }
}
