package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

final class MyTask implements Runnable {
  @Override public void run() {
    System.out.println("My task is started running...");
    throw new ArithmeticException();
  }
}
public class CatchExceptionInDifferentThread {

  public static void main(String[] args) {
    ExecutorService threadPool = new MyThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>());
    threadPool.execute(new MyTask());
    threadPool.shutdown();
  }
}
