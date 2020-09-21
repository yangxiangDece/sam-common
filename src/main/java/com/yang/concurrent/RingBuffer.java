package com.yang.concurrent;

import java.util.Arrays;

/**
 * 在RingBuffer中设置了两个指针，head和tail。head指向下一次读的位置，tail指向的是下一次写的位置。RingBuffer可用一个数组进行存储，数组内元素的内存地址是连续的，
 * 这是对CPU缓存友好的——也就是说，在硬件级别，数组中的元素是会被预加载的，因此在RingBuffer中，CPU无需时不时去主内存加载数组中的下一个元素。
 * 通过对head和tail指针的移动，可以实现数据在数组中的环形存取。当head==tail时，说明buffer为空，当head==(tail+1)%bufferSize则说明buffer满了。
 * <p>
 * 在进行读操作的时候，我们只修改head的值，而在写操作的时候我们只修改tail的值。在写操作时，我们在写入内容到buffer之后才修改tail的值；
 * 而在进行读操作的时候，我们会读取tail的值并将其赋值给copyTail。赋值操作是原子操作。所以在读到copyTail之后，从head到copyTail之间一定是有数据可以读的，
 * 不会出现数据没有写入就进行读操作的情况。同样的，读操作完成之后，才会修改head的数值；而在写操作之前会读取head的值判断是否有空间可以用来写数据。
 * 所以，这时候tail到head - 1之间一定是有空间可以写数据的，而不会出现一个位置的数据还没有读出就被写操作覆盖的情况。这样就保证了RingBuffer的线程安全性。
 */
public class RingBuffer<T> {

    private final static int DEFAULT_SIZE = 1024;
    private Object[] buffer;
    private int head = 0;
    private int tail = 0;
    private int bufferSize;

    public RingBuffer() {
        this.bufferSize = DEFAULT_SIZE;
        this.buffer = new Object[bufferSize];
    }

    public RingBuffer(int initSize) {
        this.bufferSize = initSize;
        this.buffer = new Object[bufferSize];
    }

    private Boolean empty() {
        return head == tail;
    }

    private Boolean full() {
        return (tail + 1) % bufferSize == head;
    }

    public void clear() {
        Arrays.fill(buffer, null);
        this.head = 0;
        this.tail = 0;
    }

    public Boolean put(Object v) {
        if (full()) {
            return false;
        }
        buffer[tail] = v;
        tail = (tail + 1) % bufferSize;
        return true;
    }

    public Object get() {
        if (empty()) {
            return null;
        }
        Object result = buffer[head];
        head = (head + 1) % bufferSize;
        return result;
    }

    public Object[] getAll() {
        if (empty()) {
            return new Object[0];
        }
        int copyTail = tail;
        int cnt = head < copyTail ? copyTail - head : bufferSize - head + copyTail;
        Object[] result = new String[cnt];
        if (head < copyTail) {
            for (int i = head; i < copyTail; i++) {
                result[i - head] = buffer[i];
            }
        } else {
            for (int i = head; i < bufferSize; i++) {
                result[i - head] = buffer[i];
            }
            for (int i = 0; i < copyTail; i++) {
                result[bufferSize - head + i] = buffer[i];
            }
        }
        head = copyTail;
        return result;
    }
}
