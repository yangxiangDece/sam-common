package com.yang.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 1、unpark函数为线程提供“许可(permit)”，线程调用park函数则等待“许可”。这个有点像信号量，但是这个“许可”是不能叠加的，“许可”是一次性的。
 * 2、比如线程B连续调用了三次unpark函数，当线程A调用park函数就使用掉这个“许可”，如果线程A再次调用park，则进入等待状态。
 * 3、注意，unpark函数可以先于park调用。比如线程B调用unpark函数，给线程A发了一个“许可”，那么当线程A调用park时，它发现已经有“许可”了，那么它会马上再继续运行。
 *
 */
public class LockSupportTest {

    private static Thread mainThread;

    public static void main(String[] args) throws Exception {
        mainThread=Thread.currentThread();
        Thread thread=new Thread(new MyThread());
        thread.start();

        TimeUnit.SECONDS.sleep(5);

        //park、与unpark不区分先后顺序调用，如果unpark方法先调用，那么也会等待调用park以后，再执行unpark
        System.out.println("让主线程阻塞...");
        LockSupport.park(mainThread);
        System.out.println("主线程继续执行...");
    }

    private static class MyThread implements Runnable {

        @Override
        public void run() {
            System.out.println("111111");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //唤醒主线程
            LockSupport.unpark(mainThread);
            System.out.println("22222");
        }
    }
}
