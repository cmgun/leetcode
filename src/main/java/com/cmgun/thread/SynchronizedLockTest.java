package com.cmgun.thread;

/**
 * 同步锁测试，多线程
 */
public class SynchronizedLockTest {

    public static Boolean isWork = false;
    public static boolean work() {
        synchronized (isWork) {
            if (isWork == true) {
                return false;
            }
            isWork = true;
            try {
                isWork.wait(3000);
            } catch (InterruptedException e) {

            }
            isWork = false;
            return true;
        }
    }

    /**
     * 会抛出异常，因为同步锁的对象，与后续wait的对象不同。
     * isWork = true 这一步就会赋予isWork一个新的Boolean对象，与前面synchronized获取的对象不同了。
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T1:" + work());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T2:" + work());
            }
        });
        t1.start();
        t2.start();
    }
}
