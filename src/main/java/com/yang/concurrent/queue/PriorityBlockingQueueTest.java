package com.yang.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 同PriorityQueue一样的优先级队列，但是PriorityBlockingQueue是阻塞队列
 */
public class PriorityBlockingQueueTest {

    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<>(2);
        blockingQueue.put(112);
        blockingQueue.put(18);
        blockingQueue.put(36);
        blockingQueue.put(-3);
        blockingQueue.put(-12);
        blockingQueue.put(99);

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }
}
