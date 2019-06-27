package com.cmgun.thread.demo.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by cmgun on 2019/6/27
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    /**
     * 线程池最大数量限制
     */
    private static final int MAX_WORKER_NUMBERS = 10;

    /**
     * 线程池默认工作者数量
     */
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    /**
     * 线程池最小数量
     */
    private static final int MIN_WORKER_NUMBERS = 1;

    /**
     * 工作队列
     */
    private final BlockingQueue<Job> jobs = new LinkedBlockingQueue<>();

    /**
     * 工作者队列，同步队列
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

    /**
     * 工作者线程数量
     */
    private int workerNum = DEFAULT_WORKER_NUMBERS;

    /**
     * 线程编号
     */
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS
                : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
        initializeWorkers(workerNum);
    }

    /**
     * 初始化工作者线程
     * @param num
     */
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    /**
     * 工作者，处理Job
     */
    private class Worker implements Runnable {

        /**
         * 是否工作
         */
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                while (jobs.isEmpty()) {
                    // 没有任务，进行等待
                    try {
                        jobs.wait();
                    } catch (InterruptedException e) {
                        // 线程被中断
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                try {
                    // 有任务，取第一个
                    Job job = jobs.take();
                    job.run();
                } catch (InterruptedException e) {
                    // 线程中断
                    Thread.currentThread().interrupt();
                    return;
                }

            }
        }

        /**
         * 结束工作者线程
         */
        public void shutdown() {
            this.running = false;
        }
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            // 添加一个工作
            try {
                jobs.put(job);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorkers(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("illegal num");
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
}
