package com.yang.concurrent.threadpool;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPool<Runnable> threadPool = new DefaultThreadPool<>();
        threadPool.addWorkers(10);
        for (int i = 0; i < 150; i++) {
            threadPool.execute(() -> {
                System.out.println("线程  " + Thread.currentThread().getName() + "  开始执行");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程  " + Thread.currentThread().getName() + "  结束");
            });
        }
    }
}
