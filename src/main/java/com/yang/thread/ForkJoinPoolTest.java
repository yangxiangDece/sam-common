package com.yang.thread;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {

    private final static int MAX=20;

    private static int[] nums=new int[500];

    private static Random r=new Random();

    static {
        for (int i=0;i<nums.length;i++) {nums[i]=r.nextInt();}
    }

    static class AddTask extends RecursiveTask<Long> {

        private static final long serialVersionUID = -3512413036710723615L;
        int start,end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {

            if (end-start<=MAX) {
                long sum=0L;
                for (int i=start;i<end;i++){sum+=nums[i];}
                return sum;
            } else {
                int middle = start+(start+end);
                AddTask left=new AddTask(start,middle);
                AddTask right=new AddTask(middle,end);
                left.fork();
                right.fork();

                return left.join()+right.join();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ForkJoinPool pool=new ForkJoinPool();
        AddTask task=new AddTask(0,nums.length);
        pool.execute(task);

        long result=task.join();
        System.out.println(result);
        System.in.read();
    }
}
