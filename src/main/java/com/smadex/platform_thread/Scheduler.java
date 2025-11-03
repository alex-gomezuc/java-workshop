package com.smadex.platform_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scheduler {
  private final ExecutorService executor = Executors.newFixedThreadPool(10);
  public void schedule(Runnable runnable) {
    executor.submit(runnable);
  }
}
