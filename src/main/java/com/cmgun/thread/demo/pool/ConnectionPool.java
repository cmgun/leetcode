package com.cmgun.thread.demo.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 连接池
 *
 * Created by cmgun on 2019/6/26
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.create());
            }
        }
    }

    /**
     * 释放连接，释放后通知其他消费者
     *
     * @param connection
     */
    public void release(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取连接
     * @param mills
     * @return
     * @throws InterruptedException
     */
    public Connection fetch(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                // 一直等待
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
