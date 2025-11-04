package com.smadex.virtual_threads;

import static com.smadex.virtual_threads.Demo.SCHEDULER;

import java.util.Timer;
import java.util.TimerTask;

public class WaitingOperation {

  public static void perform(String name, int durationSecs) {
    var virtualThread = VirtualThreadScheduler.CURRENT_VIRTUAL_THREAD.get();
    System.out.println("Waiting for "+name+" for "+durationSecs+" seconds");
    var timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        SCHEDULER.schedule(virtualThread);
        timer.cancel();
      }
    }, durationSecs * 1000L);
    virtualThread.yield();
  }

}
