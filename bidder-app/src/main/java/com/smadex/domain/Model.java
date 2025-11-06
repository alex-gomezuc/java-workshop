package com.smadex.domain;

import java.util.List;

public class Model {

  public record CountryInfo(String name, String code) {

  }

  public record CarrierInfo(String name, String code) {

  }

  public record ProfileInfo(String homeIP, List<String> bundles) {

  }

  public record BidRequest(String ip, String bundle) {

  }

  public record BidResponse(String bidId, double priceCents) {

  }
}
