package com.yang.thread;

import java.util.concurrent.locks.StampedLock;

/**
 * 是一个读写锁的改进，它的思想是读写锁中读不仅不阻塞读，同时也不应该阻塞写。
 * 1、在读的时候如果发生了写，则应当重读而不是在读的时候直接阻塞写！
 * 2、 因为在读线程非常多而写线程比较少的情况下，写线程可能发生饥饿现象，也就是因为大量的读线程存在并且读线程都阻塞写线程，
 * 因此写线程可能几乎很少被调度成功！当读执行的时候另一个线程执行了写，则读线程发现数据不一致则执行重读即可。
 * 所以读写都存在的情况下，使用StampedLock就可以实现一种无障碍操作，即读写之间不会阻塞对方，但是写和写之间还是阻塞的！
 */
public class StampedLockTest {

    public static void main(String[] args) {

    }

    private static class MyObject {
        private int a;
        private final StampedLock lock = new StampedLock();

        private void actionReadWrite(int a) {
            long stamp = lock.readLock();
            try {
                while (a == 0) {
                    //将读锁转换为写锁
                    long ws = lock.tryConvertToOptimisticRead(stamp);
                    if (ws != 0L) {
                        stamp = ws;
                        this.a = a;
                        break;
                    } else {
                        //读锁转换为写锁失败，就释放之前的读锁
                        lock.unlockRead(stamp);
                        stamp = lock.writeLock();
                    }
                }
            } finally {
                lock.unlock(stamp);
            }
        }

        public void optimisticRead() {
            //获取乐观锁
            long stamp = lock.tryOptimisticRead();
            int c = a;
            //因为是乐观锁，所以需要判断是否值已经被更改了，如果被更改了就重新获取，可以用while需要像CAS那样循环获取，也可以用读锁来获取
            if (!lock.validate(stamp)) {
                stamp = lock.readLock();
                c = a;
                System.out.println(c);
            }
            lock.unlockRead(stamp);
        }

        public void read() {
            long stamp = lock.readLock();
            int c = a;
            System.out.println(c);
            lock.unlockRead(stamp);
        }

        public void write() {
            long stamp = lock.writeLock();
            a = 12;
            lock.unlockWrite(stamp);
        }
    }
}
