package com.anz.magneto.service.download;

import com.sun.tools.javac.Main;
import java.net.InetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Slf4j
public class MainApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(MainApplication.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }

  @Component
  static class ApplicationAppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
      final var localHost = InetAddress.getLocalHost();
      log.info("hostAddress: {}", localHost.getHostAddress());
      log.info("hostName: {}", localHost.getHostName());
      log.info("canonicalHostName: {}", localHost.getCanonicalHostName());
      log.info("Application started with sourceArgs: {}, optionArgs: {}", args.getSourceArgs(),
          args.getNonOptionArgs());
      args.getOptionNames()
          .forEach(s -> log.info("Option  name: {} value: {}", s, args.getOptionValues(s)));
    }
  }
}
