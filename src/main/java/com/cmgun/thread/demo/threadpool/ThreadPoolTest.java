package com.cmgun.thread.demo.threadpool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cmgun on 2019/6/28
 */
public class ThreadPoolTest {

    private static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) throws Exception {
        System.out.println("main start");
        DefaultThreadPool pool = new DefaultThreadPool();
        for (int i = 0; i < 30; i++) {
            pool.execute(new TestRunnable(num.incrementAndGet()));
        }
        while (pool.getJobSize() > 0) {
            // do nothing
        }
        System.out.println("shutdown prepare");
        pool.shutdown();
        // 等所有工作线程都关闭，实际应该由线程池控制，后续修改
        Thread.sleep(20000);
        System.out.println("main end");
    }

    static class TestRunnable implements Runnable {

        private int num;

        public TestRunnable(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("Thread-" + num + " start.");
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread-" + num + " end.");
        }
    }
}
