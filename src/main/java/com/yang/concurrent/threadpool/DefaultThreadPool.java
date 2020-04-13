package com.yang.concurrent.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    // 线程池最大工作者数量
    private static final int MAX_WORKER_NUMBERS = 10;
    // 线程默认工作者数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    // 线程池最小工作者数量
    private static final int MIN_WORKER_NUMBERS = 5;
    // 工作任务
    private final LinkedList<Job> jobs = new LinkedList<>();
    // 工作者
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());
    // 工作者线程数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    // 线程编号生产
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : Math.max(num, MIN_WORKER_NUMBERS);
        initializeWorkers(workerNum);
    }

    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            // 添加一个工作 然后通知线程有工作任务了
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        workers.forEach(Worker::shutdown);
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            num = Math.min((num + this.workerNum), MAX_WORKER_NUMBERS);
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void reduceWorker(int num) {
        synchronized (jobs) {
            // 判断参数
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("beyond workNum");
            }
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    class Worker implements Runnable {

        // 标识线程是否在工作
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job;
                synchronized (jobs) {
                    // 如果工作列表是空的，那么就wait
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            // 响应中断
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    // 有任务了
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {
                        // 忽略执行中的异常
                    }
                }
            }
        }

        public void shutdown() {
            this.running = false;
        }
    }
}
