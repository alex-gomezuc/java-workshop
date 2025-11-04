package com.smadex.thread_examples;

import java.util.concurrent.Executors;

public class Demo {

  static void main(String[] args) {
    Thread.ofPlatform().start(() -> System.out.println(Thread.currentThread()));
    try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
      executorService.submit(() -> System.out.println(Thread.currentThread()));
      executorService.submit(() -> System.out.println(Thread.currentThread()));
      executorService.submit(() -> System.out.println(Thread.currentThread()));
      executorService.submit(() -> System.out.println(Thread.currentThread()));
      executorService.submit(() -> System.out.println(Thread.currentThread()));
      executorService.submit(() -> System.out.println(Thread.currentThread()));
      executorService.submit(() -> System.out.println(Thread.currentThread()));
      executorService.submit(() -> System.out.println(Thread.currentThread()));
      executorService.submit(() -> System.out.println(Thread.currentThread()));
    }
  }

}
