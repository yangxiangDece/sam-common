package com.yang.dubbo.singlelongconnection;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 请求数据获取类，用来挂起和唤醒挂起的线程
 */
public class RpcResponseFuture {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Long requestId;

    public RpcResponseFuture(Long requestId) {
        this.requestId = requestId;
    }

    public byte[] get() {
        byte[] bytes = RpcContainer.getResponse(requestId);
        if (bytes == null) {
            lock.lock();
            try {
                System.out.println("请求id:" + requestId + ",请求结果尚未返回，线程挂起");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        System.out.println("请求id:" + requestId + ",请求结果返回了");
        // 再次获取
        return RpcContainer.getResponse(requestId);
    }

    public void rpcIsDone() {
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
}
