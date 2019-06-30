package com.cmgun.thread.demo.webserver;

import com.cmgun.thread.demo.threadpool.DefaultThreadPool;
import com.cmgun.thread.demo.threadpool.ThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简单的基于线程池的服务器
 *
 * Created by cmgun on 2019/6/30
 */
public class SimpleHttpServer {

    /**
     * 处理请求的线程池
     */
    private static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>();

    /**
     * 根路径
     */
    private static String basePath;

    /**
     * 服务器socket
     */
    private static ServerSocket serverSocket;

    /**
     * 监听端口
     */
    private static int port = 8080;

    /**
     * 设置监听端口
     * @param port
     */
    public static void setPort(int port) {
        if (port > 0) {
            SimpleHttpServer.port = port;
        }
    }

    /**
     * 设置根路径
     * @param basePath
     */
    public static void setBasePath(String basePath) {
        if (basePath != null) {
            File file = new File(basePath);
            if (file.exists() && file.isDirectory()) {
                SimpleHttpServer.basePath = basePath;
            }
        }
    }

    public static void start() throws Exception {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }


    /**
     * 请求处理
     */
    private static class HttpRequestHandler implements Runnable {

        /**
         * 请求socket
         */
        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter printWriter = null;
            InputStream inputStream = null;
            try {
                // 读取socket流
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                String filePath = basePath + header.split(" ")[1];
                printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println("HTTP/1.1 200 OK");
                printWriter.println("Server: CMGUN");
                // 如果请求资源后缀为jpg，读取并输出
                if (filePath.endsWith("jps")) {
                    inputStream = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i;
                    while ((i = inputStream.read()) != -1) {
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
//                    printWriter.println("HTTP/1.1 200 OK");
//                    printWriter.println("Server: CMGUN");
                    printWriter.println("Content-Type: image/jpeg");
                    printWriter.println("Content-Length: " + array.length);
                    printWriter.println("");
                    socket.getOutputStream().write(array, 0, array.length);
                } else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    //printWriter = new PrintWriter(socket.getOutputStream());
                    printWriter.println("Content-Type: text/html; charset=UTF-8");
                    printWriter.println("");
                    // 读html内容
                    String line;
                    while ((line = br.readLine()) != null) {
                        printWriter.println(line);
                    }
                }
                printWriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
                if (printWriter != null) {
                    printWriter.println("HTTP/1.1 500");
                    printWriter.println("");
                    printWriter.flush();
                }
            } finally {
                close(br, inputStream, reader, printWriter, socket);
            }
        }
    }

    private static void close(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
