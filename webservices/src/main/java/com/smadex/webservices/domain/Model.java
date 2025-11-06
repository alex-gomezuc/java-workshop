package com.smadex.webservices.domain;

import java.math.BigDecimal;
import java.util.List;

public class Model {

  public record CountryInfo(String name, String code) {
  }
  public record CarrierInfo(String name, String code) {
  }
  public record ProfileInfo(String homeIP, List<String> bundles) {
  }
}
