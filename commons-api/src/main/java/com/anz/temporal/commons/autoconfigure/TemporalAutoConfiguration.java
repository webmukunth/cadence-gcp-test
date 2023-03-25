package com.anz.temporal.commons.autoconfigure;

import com.anz.temporal.commons.Constants;
import com.anz.temporal.commons.utils.TraceUtil;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.reporters.RemoteReporter;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.jaegertracing.spi.Sampler;
import io.jaegertracing.thrift.internal.senders.UdpSender;
import io.temporal.client.ActivityCompletionClient;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.opentracing.OpenTracingClientInterceptor;
import io.temporal.opentracing.OpenTracingOptions;
import io.temporal.opentracing.OpenTracingSpanContextCodec;

import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.workflow.Workflow;
import com.uber.m3.tally.Scope;
import io.opentracing.Tracer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Workflow.class)
@EnableConfigurationProperties(TemporalConfigurationProperties.class)
@Slf4j
public class TemporalAutoConfiguration {

  @Value("${spring.application.name:no-name}")
  private String applicationName;

  @Bean
  @ConditionalOnMissingBean
  public WorkflowClient workflowClient(
          TemporalConfigurationProperties prop, WorkflowServiceStubs workflowServiceStubs) {

    final var clientOptions =
            WorkflowClientOptions.newBuilder()
                    .setInterceptors(new OpenTracingClientInterceptor(TraceUtil.getJaegerOpenTracingOptions(prop)))
                    .build();
    final var ret = WorkflowClient.newInstance(workflowServiceStubs,clientOptions);
    log.info("Initialized workflowClient {} for host {} ", ret , prop.getHost());
    return ret;
  }

  @Bean
  @ConditionalOnMissingBean
  public WorkflowServiceStubs workflowServiceTChannel(TemporalConfigurationProperties prop,
                                                         Scope ms, Tracer tracer) {

    log.info("{} temporalConfiguration: {}, tracer: {}", applicationName, prop, tracer);

    final var host = prop.getHost();
    final var port = prop.getPort();

    final var opt = WorkflowServiceStubsOptions.newBuilder()
            .setMetricsScope(ms).build();

    final var service = WorkflowServiceStubs.newServiceStubs(opt);

   /* final var opt =  new WorkflowClientOptions.Builder
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
*/
    return service;
  }

  @Bean
  @ConditionalOnMissingBean
  public ActivityCompletionClient activityCompletionClient(WorkflowClient workflowClient) {
    return workflowClient.newActivityCompletionClient();
  }

  /*@Bean
  @ConditionalOnMissingBean
  *//*public OpenTracingOptions getJaegerOpenTracingOptions(TemporalConfigurationProperties prop) {
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

  public OpenTracingOptions getOpenTracingOptionsForTracer(Tracer tracer) {
    OpenTracingOptions options =
            OpenTracingOptions.newBuilder()
                    .setSpanContextCodec(OpenTracingSpanContextCodec.TEXT_MAP_CODEC)
                    .setTracer(tracer)
                    .build();
    return options;
  }*/

}
