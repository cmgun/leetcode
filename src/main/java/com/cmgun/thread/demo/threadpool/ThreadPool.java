package com.cmgun.thread.demo.threadpool;

/**
 * 简单线程池
 *
 * Created by cmgun on 2019/6/27
 */
public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行一个Runnable Job
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作者线程
     * @param num
     */
    void addWorkers(int num);

    /**
     * 减少工作者线程
     * @param num
     */
    void removeWorkers(int num);

    /**
     * 得到正在等待执行的任务数量
     * @return
     */
    int getJobSize();
}
