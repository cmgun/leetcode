package com.cmgun.thread.demo;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal
 *
 * Created by cmgun on 2019/6/26
 */
public class ThreadLocalTest {

    // 第一次get()方法调用时，如果没有进行set操作，会执行该方法进行初始化，每个线程会调用一次
    private static final ThreadLocal<Long> TIME_THREADLOCAL = ThreadLocal.withInitial(() -> {
        System.out.println("withInitial");
        return System.currentTimeMillis();
    });

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws Exception {
        ThreadLocalTest.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + ThreadLocalTest.end() + " mills.");
    }
}
