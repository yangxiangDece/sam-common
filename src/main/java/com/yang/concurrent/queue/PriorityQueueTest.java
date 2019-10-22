package com.yang.concurrent.queue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * PriorityQueue 一个基于优先级的无界优先级队列。优先级队列的元素按照其自然顺序进行排序，或者根据构造队列时提供的 Comparator 进行排序，
 * 具体取决于所使用的构造方法。该队列不允许使用 null 元素也不允许插入不可比较的对象(没有实现Comparable接口的对象)。
 * <p>
 * PriorityQueue 是一个无界队列，但是初始的容量(实际是一个Object[])，随着不断向优先级队列添加元素，其容量会自动扩容，无需指定容量增加策略的细节。
 * <p>
 * 1>PriorityQueue是一种无界的，线程不安全的队列。
 * 2>PriorityQueue是一种通过数组实现的，并拥有优先级的队列。
 * 3>PriorityQueue存储的元素要求必须是可比较的对象， 如果不是就必须明确指定比较器。
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(122);
        queue.offer(32);
        queue.offer(412);
        queue.offer(452);
        queue.offer(22222);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
