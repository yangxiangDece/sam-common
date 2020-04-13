package com.yang.concurrent.threadpool;

public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行一个job，这个job需要实现Runnable
     *
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作者线程
     *
     * @param num
     */
    void addWorkers(int num);

    /**
     * 减少工作者线程
     *
     * @param num
     */
    void reduceWorker(int num);

    /**
     * 获取正在等待执行的任务数量
     *
     * @return
     */
    int getJobSize();
}
