package com.anz.cadence.commons.autoconfigure;

import com.anz.cadence.commons.Constants;
import com.uber.cadence.client.ActivityCompletionClient;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.workflow.Workflow;
import com.uber.m3.tally.Scope;
import com.uber.tchannel.api.SubChannel;
import com.uber.tchannel.api.TChannel;
import io.opentracing.Tracer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Workflow.class)
@EnableConfigurationProperties(CadenceConfigurationProperties.class)
@Slf4j
public class CadenceAutoConfiguration {

  @Value("${spring.application.name:no-name}")
  private String applicationName;

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

  @Bean
  @ConditionalOnMissingBean
  public ActivityCompletionClient activityCompletionClient(WorkflowClient workflowClient) {
    return workflowClient.newActivityCompletionClient();
  }
}
