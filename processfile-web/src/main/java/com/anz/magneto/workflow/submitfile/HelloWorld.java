package com.anz.magneto.workflow.submitfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hw")
@Slf4j
public class HelloWorld {

  @GetMapping(path = "/hello", produces = "application/json")
  public HelloWorldResponse hello() {
    log.info("Get a request");
    return new HelloWorldResponse("world");
  }

  @Data
  @AllArgsConstructor
  static class HelloWorldResponse {
    final String hello;
  }
}
