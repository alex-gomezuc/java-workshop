package com.smadex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class BidderApplication {

  @Value("${base.url}")
  private String baseUrl;

  public static void main(String[] args) {
    SpringApplication.run(BidderApplication.class, args);
  }

  @Bean
  public RestClient restClient() {
    return RestClient.builder().baseUrl(baseUrl).build();
  }
}
