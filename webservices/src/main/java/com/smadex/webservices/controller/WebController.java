package com.smadex.webservices.controller;

import static com.smadex.webservices.utils.RequestUtils.logAndWait;

import com.smadex.webservices.domain.Model.CarrierInfo;
import com.smadex.webservices.domain.Model.CountryInfo;
import com.smadex.webservices.domain.Model.ProfileInfo;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class WebController {

  @GetMapping("/{ip}/country")
  public CountryInfo getCountry(@PathVariable String ip) {
    logAndWait("getCountry");
    return new CountryInfo("Spain", "ES");
  }

  @GetMapping("/{ip}/carrier")
  public CarrierInfo getCarrier(@PathVariable String ip) {
    logAndWait("getCarrier");
    return new CarrierInfo("Vodafone", "VF");
  }

  @GetMapping("/{ip}/profile1")
  public ProfileInfo getProfile1(@PathVariable String ip) {
    logAndWait("getProfile1");
    return new ProfileInfo("1 - " + ip, List.of("com.app.one", "com.app.two"));
  }

  @GetMapping("/{ip}/profile2")
  public ProfileInfo getProfile2(@PathVariable String ip) {
    logAndWait("getProfile2");
    return new ProfileInfo("2 - " + ip, List.of("com.app.three", "com.app.four"));
  }

}
