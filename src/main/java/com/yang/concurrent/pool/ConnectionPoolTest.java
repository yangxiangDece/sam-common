package com.yang.concurrent.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据库连接池 模拟实现
 */
public class ConnectionPoolTest {

    private static ConnectionPool pool = new ConnectionPool(10);
    // 保证所有线程同时开始
    private static CountDownLatch start = new CountDownLatch(1);
    // 让main线程等待所有其他线程执行完了以后再执行
    private static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        // 线程数量
        int threadCount = 20;
        end = new CountDownLatch(threadCount);
        // 每个线程获取连接次数
        int count = 25;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread-" + i);
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke：" + (threadCount * count));
        System.out.println("got connection：" + got);
        System.out.println("notGot connection：" + notGot);
    }

    static class ConnectionRunner implements Runnable {

        private int count;
        private AtomicInteger got;
        private AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (count > 0) {
                try {
                    // 从线程池获取连接，如果1000ms内无法获取到，将会返回null
                    // 分别统计连接获取到的got  和 未获取到的 notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            // 提交事务完成后 释放连接资源
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        System.out.println("未获取到连接" + Thread.currentThread().getName());
                        notGot.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
