package com.yang.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 有若干条线程 假设十个 必须按照特定顺序循环执行
 * 0执行 1执行 2执行..... 9执行完 0继续执行 不该执行的线程必须被阻塞 不能空跑 创建的线程数不能超过循环规模
 */
public class ThreadWorking {

    private static int value = 0;
    private final static int size = 10;
    private final static List<Thread> THREADS = new ArrayList<>(size);

    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            Thread t = new Thread(new Task(i), "第" + i + "个线程");
            t.start();
            THREADS.add(t);
        }
        LockSupport.unpark(THREADS.get(0));
        LockSupport.park();
    }

    static class Task implements Runnable {
        private int num;

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            for (; ; ) {
                LockSupport.park();
                try {
                    TimeUnit.MILLISECONDS.sleep(500L);
                } catch (InterruptedException ignored) {
                }
                System.out.println(Thread.currentThread().getName() + "输出" + (value++));
                LockSupport.unpark(THREADS.get(num == size - 1 ? 0 : num + 1));
            }
        }
    }
}
