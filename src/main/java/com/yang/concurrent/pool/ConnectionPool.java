package com.yang.concurrent.pool;

import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionPool {
    private final LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            }
            long future = System.currentTimeMillis() + mills;
            long remaining = mills;
            while (pool.isEmpty() && remaining > 0) {
                pool.wait(remaining);
                // 有可能还没有等待到指定时间 就被唤醒了
                remaining = future - System.currentTimeMillis();
            }
            if (!pool.isEmpty()) {
                return pool.removeFirst();
            }
            return null;
        }
    }
}
