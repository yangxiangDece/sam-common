package com.yang.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {

    public static void main(String[] args) throws Exception {
        int size = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(size);
        for (int i = 0; i < size; i++) {
            int finalI = i + 1;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                    System.out.println("线程" + finalI + "准备好了，等待其他线程...");
                    cyclicBarrier.await();
                    System.out.println("线程" + finalI + "执行完毕！！");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("主线程结束....");
    }
}
