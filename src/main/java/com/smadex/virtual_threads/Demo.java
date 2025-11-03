package com.smadex.virtual_threads;

public class Demo {

  public static final VirtualThreadScheduler SCHEDULER = new VirtualThreadScheduler();

  static void main() {
    new Thread(SCHEDULER::start).start();
    for (int i = 0; i < 1000; i++) {
      VirtualThread vt1 = new VirtualThread(() -> {
        System.out.println("1.1");
        System.out.println("1.2");
        WaitingOperation.perform("VT1", 10);
        System.out.println("1.3");
        System.out.println("1.4");
      });

      VirtualThread vt2 = new VirtualThread(() -> {
        System.out.println("1.1");
        System.out.println("1.2");
        WaitingOperation.perform("VT2", 6);
        System.out.println("1.3");
        System.out.println("1.4");
      });
      SCHEDULER.schedule(vt1);
      SCHEDULER.schedule(vt2);
    }
  }

}
