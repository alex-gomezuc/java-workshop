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
    try (var structuredTaskScope = StructuredTaskScope.open()) {
      var ip = bidRequest.ip();
      var countryFuture = structuredTaskScope.fork(() -> countryService.getCountryInfo(ip));
      var carrierFuture = structuredTaskScope.fork(() -> carrierService.getCarrierInfo(ip));
      var profileFuture = structuredTaskScope.fork(
          () -> profileService.getFirstProfileAvailable(ip));

      structuredTaskScope.join();

      var country = countryFuture.get();
      var carrier = carrierFuture.get();
      var profile = profileFuture.get();

      return computePrice(country, carrier, profile);

    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Price computation was interrupted", e);

    } catch (FailedException failedException) {
      logger.error("One or more tasks failed", failedException);
      throw new RuntimeException("Failed to retrieve necessary information for price computation",
          failedException);

    } catch (Exception e) {
      throw new RuntimeException("Failed to compute price", e);
    }
  }

  private double computePrice(CountryInfo country, CarrierInfo carrier, ProfileInfo profile) {
    return Math.random() * 10;
  }

}
