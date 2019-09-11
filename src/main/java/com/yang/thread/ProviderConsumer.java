package com.yang.thread;

import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ProviderConsumer {

    public static void main(String[] args) {
        MyLinkedList<String> list=new MyLinkedList<>();
        Thread producer=new Thread(()-> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(UUID.randomUUID().toString());
            }
        });
        Thread consumer=new Thread(()->{
            while (true) {
                list.get();
            }
        });

        producer.start();
        consumer.start();
    }

    static class MyLinkedList<T> {
        private LinkedList<T> list=new LinkedList<>();

        public void add(T t) {
            synchronized (this) {
                while (list.size()== 10) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("添加元素："+t);
                list.add(t);
                this.notifyAll();
            }
        }

        public void get() {
            synchronized (this) {
                while (list.size()==0) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("获取到元素:"+list.getFirst());
                list.removeFirst();
                this.notifyAll();
            }
        }
    }
}

