package com.smadex;

import com.smadex.domain.Model.BidRequest;
import com.smadex.domain.Model.BidResponse;
import com.smadex.services.PriceService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
