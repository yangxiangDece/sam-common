package com.yang.concurrent;

import java.util.concurrent.TimeUnit;

public class DeathLockTest {

    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try {
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (B) {
                try {
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println("1");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
