package com.cmgun.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * print odd number and even number in different thread, the result should be in order.
 *
 * Created by cmgun on 2019/5/9
 */
public class ParallelPrintNums {

    private static int number = 1;

    private static final Lock lock = new ReentrantLock();

    private static final Condition evenCondition = lock.newCondition();

    private static final Condition oddCondition = lock.newCondition();

    public static void main(String[] args) {
        // print result
        int max = 100;
        Thread oddThread = new Thread(new OddNumber(max));
        Thread evenThread = new Thread(new EvenNumber(max));
        oddThread.setName("odd");
        evenThread.setName("even");
        oddThread.start();
        evenThread.start();
    }

    /**
     * print odd number
     */
    static class OddNumber implements Runnable {

        private int max;

        public OddNumber(int max) {
            this.max = max;
        }

        @Override
        public void run() {
            while (number <= max) {
                try {
                    lock.lock();
                    System.out.println("Odd :" + number);
                    number++;
                    // lock odd thread
                    oddCondition.await();
                    // notify even thread
                    evenCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    /**
     * print even number
     */
    static class EvenNumber implements Runnable {

        private int max;

        public EvenNumber(int max) {
            this.max = max;
        }

        @Override
        public void run() {
            while (number <= max) {
                try {
                    lock.lock();
                    System.out.println("Even :" + number);
                    number++;
                    // notify even thread
                    oddCondition.signal();
                    // lock odd thread
                    evenCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
