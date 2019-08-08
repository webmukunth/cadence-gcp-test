package com.anz.magneto.autoconfigure;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

@ConfigurationProperties(prefix = "cadence")
@ToString
@Getter
@Setter
public class CadenceConfigurationProperties {

  private String host;
  private int port;
  private boolean emitMetric;
  @DurationUnit(ChronoUnit.DAYS)
  private Duration retentionPeriod = Duration.ofDays(7);
}
