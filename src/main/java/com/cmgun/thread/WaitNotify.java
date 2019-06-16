package com.cmgun.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * object wait and notify
 *
 * Created by cmgun on 2019/6/16
 */
public class WaitNotify {

    private static boolean flag = true;
    private static Object lock = new Object();

    /**
     * 1、wait/notify/notify使用前要对对象进行加锁
     * 2、wait调用后线程状态由 RUNNING → WAITING，并将当前线程方到对象的等待队列
     * 3、notify/notifyAll调用后，等待线程不会立刻从wait返回，需要等notify的线程释放锁之后，wait线程才有机会返回
     * 4、notify/notifyAll，将一个/全部等待线程从等待队列移到同步队列，线程状态 WAITING → BLOCKED
     * 5、wait返回前提是要获取到调用对象的锁
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread wait = new Thread(new Wait(), "WaitThread");
        wait.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread notify = new Thread(new Notify(), "NotifyThread");
        notify.start();
    }

    private static class Wait implements Runnable {
        @Override
        public void run() {
            // add lock
            synchronized (lock) {
                while (flag) {
                    try {
                        // 1
                        System.out.println(Thread.currentThread() + " wait at:"
                                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // flag is false
                // 4
                System.out.println(Thread.currentThread() + " running at:"
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    private static class Notify implements Runnable {
        @Override
        public void run() {
            // add lock
            synchronized (lock) {
                try {
                    // 2 or 3
                    System.out.println(Thread.currentThread() + " hold lock, notify at"
                            + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    lock.notifyAll();
                    flag = false;
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            // lock again
            synchronized (lock) {
                try {
                    // 2 or 3
                    System.out.println(Thread.currentThread() + " hold lock again, sleep at"
                            + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
