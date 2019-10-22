package com.yang.concurrent;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {

    public static void main(String[] args) throws Exception {
        // 不推荐使用：new ForkJoinPool()或者new ForkJoinPool(N)这些方式来进行操作，这并不是ForkJoinPool作者Doug Lea推荐的使用方式
        //ForkJoinPool pool = new ForkJoinPool();
        // 推荐使用：ForkJoinPools类有一个静态方法commonPool()，这个静态方法所获得的ForkJoinPools实例是由整个应用进程共享的，并且它适合绝大多数的应用系统场景。
        // 使用commonPool通常可以帮助应用程序中多种需要进行归并计算的任务共享计算资源，从而使后者发挥最大作用（ForkJoinPools中的工作线程在闲置时会被缓慢回收，
        // 并在随后需要使用时被恢复），而这种获取ForkJoinPools实例的方式，才是Doug Lea推荐的使用方式
        ForkJoinPool pool = ForkJoinPool.commonPool();
        AddTask task = new AddTask(0, nums.length);
        pool.execute(task);

        long result = task.join();
        System.out.println(result);
        System.in.read();
    }

    private final static int MAX = 20;
    private static int[] nums = new int[500];
    private static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt();
        }
    }

    static class AddTask extends RecursiveTask<Long> {
        private static final long serialVersionUID = -3512413036710723615L;
        int start, end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            } else {
                int middle = start + (start + end);
                AddTask left = new AddTask(start, middle);
                AddTask right = new AddTask(middle, end);
                left.fork();
                right.fork();
                return left.join() + right.join();
            }
        }
    }
}
