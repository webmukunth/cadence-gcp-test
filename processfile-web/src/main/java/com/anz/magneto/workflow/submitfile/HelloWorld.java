package com.anz.magneto.workflow.submitfile;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
  @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
  @RequiredArgsConstructor
  static class HelloWorldResponse {

    @NonNull
    final String hello;
  }
}
