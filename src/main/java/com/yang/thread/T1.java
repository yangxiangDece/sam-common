package com.yang.thread;

import java.util.concurrent.TimeUnit;

public class T1 {

    public static void main(String[] args) {

        Thread t1=new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1执行了");
        });
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main方法执行了！");

    }
}
