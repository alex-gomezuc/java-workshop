package com.smadex.controller;

import com.smadex.domain.Model.BidRequest;
import com.smadex.domain.Model.BidResponse;
import com.smadex.domain.RequestContext;
import com.smadex.services.PriceService;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidController {

  private final PriceService priceService;

  public BidController(PriceService priceService) {
    this.priceService = priceService;
  }

  @PostMapping("/bid")
  public BidResponse hello(@RequestBody BidRequest bidRequest) {
    var ip = bidRequest.ip();
    var price = RequestContext.withRequestIp(ip).call(() -> priceService.getPrice(bidRequest));
    return new BidResponse(UUID.randomUUID().toString(), price);
  }

}
