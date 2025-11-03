package com.smadex.virtual_threads;

import java.util.concurrent.atomic.AtomicInteger;
import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

public class VirtualThread {

  public static final AtomicInteger COUNTER = new AtomicInteger(1);
  public static final ContinuationScope SCOPE = new ContinuationScope("VirtualThread");

  private final int id;
  private final Continuation cont;

  public VirtualThread(Runnable runnable) {
    id = COUNTER.getAndIncrement();
    cont = new Continuation(SCOPE, runnable);
  }

  public void run() {
    System.out.println("Virtual Thread " + id + " running on " + Thread.currentThread());
    cont.run();
  }

  public void yield() {
    Continuation.yield(SCOPE);
  }
}
