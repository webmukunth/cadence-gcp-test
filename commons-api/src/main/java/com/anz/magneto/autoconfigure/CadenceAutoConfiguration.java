package com.anz.magneto.autoconfigure;

import com.anz.magneto.commons.Constants;
import com.anz.magneto.utils.TraceUtil;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.m3.tally.Buckets;
import com.uber.m3.tally.Capabilities;
import com.uber.m3.tally.RootScopeBuilder;
import com.uber.m3.tally.Scope;
import com.uber.m3.tally.StatsReporter;
import com.uber.m3.util.Duration;
import com.uber.tchannel.api.SubChannel;
import com.uber.tchannel.api.TChannel;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.opentracing.Tracer;
import io.opentracing.contrib.java.spring.jaeger.starter.TracerBuilderCustomizer;
import io.opentracing.contrib.spring.tracer.configuration.TracerAutoConfiguration;
import io.opentracing.tag.Tags;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CadenceConfigurationProperties.class)
@AutoConfigureAfter(TracerAutoConfiguration.class)
@Slf4j
public class CadenceAutoConfiguration {

  @Value("${spring.application.name:no-name}")
  private String applicationName;

  @Bean
  @ConditionalOnMissingBean
  public TraceUtil traceUtil(Tracer tracer, MeterRegistry registry) {
    return new TraceUtil(tracer, registry);
  }

  @Bean
  @ConditionalOnMissingBean
  public Scope metricScope(MeterRegistry meterRegistry) {
    final var sb = new RootScopeBuilder();
    final var ret = sb.reporter(new PublishToPrometheusReporter(meterRegistry))
        .reportEvery(Duration.ofSeconds(30));
    log.info("Initialized metricScope {}", ret);
    return ret;
  }

  @Bean
  MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry -> {
      registry.config().commonTags(
          Tags.COMPONENT.getKey(), "cadence",
          "application", applicationName,
          "clientDomain", Constants.DOMAIN,
          "clientInstance", Constants.INSTANCE_NAME
      );
    };
  }

  @Bean
  TracerBuilderCustomizer tracerBuilderCustomizer() {
    return builder -> builder.withTags(
        Map.of(
            "application", applicationName,
            "clientDomain", Constants.DOMAIN,
            "clientInstance", Constants.INSTANCE_NAME
        )
    );
  }

  @Bean
  @ConditionalOnMissingBean
  public WorkflowClient workflowClient(
      CadenceConfigurationProperties prop, WorkflowServiceTChannel tc) {
    final var ret = WorkflowClient.newInstance(tc, Constants.DOMAIN);
    log.info("Initialized workflowClient {}", ret);
    return ret;
  }


  @Bean
  @ConditionalOnMissingBean
  public WorkflowServiceTChannel workflowServiceTChannel(CadenceConfigurationProperties prop,
      Scope ms, Tracer tracer) {

    log.info("{} cadenceConfiguration: {}, tracer: {}", applicationName, prop, tracer);

    final var host = prop.getHost();
    final var port = prop.getPort();

    final var opt = new WorkflowServiceTChannel.ClientOptions.Builder()
        .setClientAppName(applicationName).setMetricsScope(ms).build();

    final var chnl = new TChannel.Builder(applicationName).setTracer(tracer).build();

    final SubChannel subchnl;
    try {
      InetAddress iadr = InetAddress.getByName(host);
      subchnl = chnl.makeSubChannel(opt.getServiceName()).setPeers(
          Collections.singletonList(new InetSocketAddress(iadr, port)));
      log.info("Initialized TChannel for service {} running at {}:{}", opt.getServiceName(), host,
          port);
    } catch (Exception e) {
      chnl.shutdown();
      throw new RuntimeException("error occured while connecting to " + host + ":" + port, e);
    }

    final var ret = new WorkflowServiceTChannel(subchnl, opt);
    log.info("Initialized WorkflowServiceTChannel {}", ret);

    return ret;
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
