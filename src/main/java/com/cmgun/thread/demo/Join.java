package com.cmgun.thread.demo;

import java.util.concurrent.TimeUnit;

/**
 * Thread join
 *
 * Created by cmgun on 2019/6/18
 */
public class Join {

    /**
     * 创建10个线程，每个线程调用前一个线程的join方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new JoinThread(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    private static class JoinThread implements Runnable {

        private Thread thread;

        public JoinThread(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
