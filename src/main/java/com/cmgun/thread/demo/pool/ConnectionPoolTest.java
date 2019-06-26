package com.cmgun.thread.demo.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 连接池测试
 *
 * Created by cmgun on 2019/6/26
 */
public class ConnectionPoolTest {

    private static ConnectionPool pool = new ConnectionPool(10);
    // 保证所有ConnectionRunner同时开始
    private static CountDownLatch start = new CountDownLatch(1);
    // main线程会等待所有ConnectionRunner结束后才会继续执行
    private static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        // 线程数量
        int threadCount = 100;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        // 保证所有线程都准备才开启
        start.countDown();
        // 保证所有ConnectionRunner都执行完才继续
        end.await();
        System.out.println("total invoke: " + (threadCount + count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection: " + notGot);
    }

    private static class ConnectionRunner implements Runnable {
        private int count;
        private AtomicInteger got;
        private AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (count > 0) {
                try {
                    Connection connection = pool.fetch(1);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.release(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
