package com.yang.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        for (int i = 0; i < barrier.getParties(); i++) {
            new Thread(new MyRunnable(barrier), "队友" + i).start();
        }
        System.out.println("main function is finished.");
    }

    private static class MyRunnable implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public MyRunnable(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    Random rand = new Random();
                    int randomNum = rand.nextInt(3000 + 1) + 1000;
                    Thread.sleep(randomNum);
                    System.out.println(Thread.currentThread().getName() + ", 通过了第" + i + "个障碍物, 使用了 " + ((double) randomNum / 1000) + "s");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
