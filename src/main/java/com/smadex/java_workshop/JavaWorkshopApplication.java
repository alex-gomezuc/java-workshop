package com.smadex.java_workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class JavaWorkshopApplication {

  public static void main(String[] args) {
    SpringApplication.run(JavaWorkshopApplication.class, args);
  }

  @RestController
  public static class TestController {

    @GetMapping("/hello")
    public String hello() {
      return "Hello, Smadex team! From " + Thread.currentThread();
    }

  }

}
