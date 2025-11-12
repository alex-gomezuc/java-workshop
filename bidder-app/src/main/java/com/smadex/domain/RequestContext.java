package com.smadex.domain;

import java.lang.ScopedValue.CallableOp;

public class RequestContext {

  public static final ScopedValue<String> REQUEST_IP = ScopedValue.newInstance();

  public static Request withRequestIp(String ip) {
    var carrier = ScopedValue.where(REQUEST_IP, ip);
    return new Request(carrier);
  }

  public static String getCurrentRequestIp() {
    return REQUEST_IP.orElseThrow(() -> new IllegalStateException("Current request ip is null"));
  }

  public static class Request {
    private ScopedValue.Carrier carrier;

    private Request(ScopedValue.Carrier carrier) {
      this.carrier = carrier;
    }

    public <T, X extends Throwable> T call(CallableOp<T, X> callableOp) throws X {
      return carrier.call(callableOp);
    }
  }

}
