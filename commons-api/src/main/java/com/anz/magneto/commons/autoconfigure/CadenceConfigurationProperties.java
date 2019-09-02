package com.anz.magneto.commons.autoconfigure;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

@ConfigurationProperties(prefix = "cadence")
@Data
public class CadenceConfigurationProperties {

  private String host;
  private int port;
  private boolean emitMetric;
  @DurationUnit(ChronoUnit.DAYS)
  private Duration retentionPeriod = Duration.ofDays(7);
}
