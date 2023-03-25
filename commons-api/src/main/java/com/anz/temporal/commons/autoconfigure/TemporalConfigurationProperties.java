package com.anz.temporal.commons.autoconfigure;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

@ConfigurationProperties(prefix = "temporal")
@Data
public class TemporalConfigurationProperties {

  private String host;
  private int port;
  private boolean emitMetric;
  @DurationUnit(ChronoUnit.DAYS)
  private Duration retentionPeriod = Duration.ofDays(7);
}
