package com.smadex.services;

import com.smadex.domain.Model.CarrierInfo;
import com.smadex.domain.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CarrierService {

  private static final Logger logger = LoggerFactory.getLogger(CarrierService.class);

  private final RestClient restClient;

  public CarrierService(RestClient restClient) {
    this.restClient = restClient;
  }

  public CarrierInfo getCarrierInfo() {
    var ip = RequestContext.getCurrentRequestIp();

    logger.info("{} CarrierService.getCarrierInfo: Start", ip);

    var carrier = restClient.get().uri("/{ip}/carrier", ip).retrieve().body(CarrierInfo.class);

    logger.info("{} CountryService.getCarrierInfo: Done", ip);
    return carrier;
  }


}
