package org.example;

import java.util.Arrays;

public class CatchExceptionInSameThread {
  public static void main(String[] args) {

    try {

      System.out.println("My task is started running...");
      throw new ArithmeticException();

    } catch (Exception exception) {
      System.err.println("exception is detected! stake trace: " + Arrays.toString(exception.getStackTrace()));
    }
  }
}
