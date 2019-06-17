package com.cmgun.thread.demo;

import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 管道通信
 *
 * Created by cmgun on 2019/6/17
 */
public class Piped {


    public static void main(String[] args) throws Exception {
        PipedWriter pipedWriter = new PipedWriter();
        PipedReader pipedReader = new PipedReader();
        // 连接输出流和输出流
        pipedWriter.connect(pipedReader);
        // main线程通过writer输出，printThread通过reader读取并输入到控制台
        Thread printThread = new Thread(new Print(pipedReader));
        printThread.start();

        int recieve = 0;
        try {
            while ((recieve = System.in.read()) != -1) {
                pipedWriter.write(recieve);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pipedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static class Print implements Runnable {

        private PipedReader pipedReader;

        public Print(PipedReader pipedReader) {
            this.pipedReader = pipedReader;
        }

        @Override
        public void run() {
            int recieve = 0;
            try {
                while ((recieve = pipedReader.read()) != -1) {
                    System.out.print((char) recieve);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (pipedReader != null) {
                    try {
                        pipedReader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
