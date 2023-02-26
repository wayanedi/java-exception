package org.example;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor extends ThreadPoolExecutor {

  public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
      TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }

  @Override
  public void afterExecute(Runnable r, Throwable t) {
    super.afterExecute(r, t);
    if (t == null && r instanceof Future<?>) {
      try {
        Object result = ((Future<?>) r).get();
      } catch (CancellationException e) {
        t = e;
      } catch (ExecutionException e) {
        t = e.getCause();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    if (t != null) {
      System.err.println("exception is detected! " + t
          + " stake trace: " + Arrays.toString(t.getStackTrace()));
      execute(r);
    }
  }
}
