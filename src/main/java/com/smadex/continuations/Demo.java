package com.smadex.continuations;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

public class Demo {

  static void main() {
    var continuation = getContinuation();
    System.out.println("Doing something else...");
    continuation.run();
    System.out.println("Doing something else...");
    continuation.run();
    System.out.println("Doing something else...");
    continuation.run();
    System.out.println("Finished!");
  }

  private static Continuation getContinuation() {
    var scope = new ContinuationScope("Virtual Threads Demo");
    return new Continuation(scope, () -> {
      System.out.println("A");
      Continuation.yield(scope);
      System.out.println("B");
      Continuation.yield(scope);
      System.out.println("C");
    });
  }

}
