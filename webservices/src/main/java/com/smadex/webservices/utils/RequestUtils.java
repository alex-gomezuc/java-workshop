package com.smadex.webservices.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtils {

  private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

  public static void logAndWait(String task) {

    var delay = 1 + (long) (Math.random() * 5);
    logger.info("Performing task: {}(). Time to complete: {} seconds. Thread: {}", task, delay, Thread.currentThread());
    try {
      Thread.sleep(delay * 1_000);
      logger.info("Done task: {}()", task);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
