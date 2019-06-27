package com.cmgun.thread.demo.connectionpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * 连接池驱动-fake
 *
 * Created by cmgun on 2019/6/26
 */
public class ConnectionDriver {

    private static class ConnectionHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
//                System.out.println("proxy connection driver invoke");
                TimeUnit.MICROSECONDS.sleep(100);
            }
            return null;
        }
    }

    public static final Connection create() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader()
                , new Class<?>[] {Connection.class}, new ConnectionHandler());
    }
}
