package com.smadex.services;

import com.smadex.domain.Model.BidRequest;
import com.smadex.domain.Model.CarrierInfo;
import com.smadex.domain.Model.CountryInfo;
import com.smadex.domain.Model.ProfileInfo;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.FailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

  private final Logger logger = LoggerFactory.getLogger(PriceService.class);

  private final CountryService countryService;
  private final CarrierService carrierService;
  private final ProfileService profileService;

  public PriceService(CountryService countryService, CarrierService carrierService,
      ProfileService profileService) {
    this.countryService = countryService;
    this.carrierService = carrierService;
    this.profileService = profileService;
  }

  public double getPrice(BidRequest bidRequest) {
  }

  private double computePrice(CountryInfo country, CarrierInfo carrier, ProfileInfo profile) {
    return Math.random() * 10;
  }

}
