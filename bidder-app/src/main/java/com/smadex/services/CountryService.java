package com.smadex.services;

import com.smadex.domain.Model.CountryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CountryService {

  private static final Logger logger = LoggerFactory.getLogger(CountryService.class);

  private final RestClient restClient;

  public CountryService(RestClient restClient) {
    this.restClient = restClient;
  }

  public CountryInfo getCountryInfo(String ip) {

    logger.info("{} CountryService.getCountryInfo: Start", ip);

    var country = restClient.get().uri("/{ip}/country", ip).retrieve().body(CountryInfo.class);

    logger.info("{} CountryService.getCountryInfo: Done", ip);
    return country;
  }


}
