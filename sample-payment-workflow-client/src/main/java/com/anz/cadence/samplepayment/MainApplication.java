package com.anz.cadence.samplepayment;

import com.anz.cadence.commons.data.PaymentRequestRepository;
import java.net.InetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = {KafkaAutoConfiguration.class})
@EnableMongoRepositories(basePackageClasses = PaymentRequestRepository.class)
@EnableCaching
@Slf4j
public class MainApplication {

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  @Component
  static class ApplicationAppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
      var localHost = InetAddress.getLocalHost();
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
