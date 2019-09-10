package com.cmgun.algorithms.sort;

import org.junit.Test;

import java.util.Random;

public class SortCompareTest {

    @Test
    public void test1() {
        compare(new Insertion(), new Selection(), 1000, 100);
        compare(new Insertion(), new Selection(), 10000, 100);
    }

    @Test
    public void test2() {
        compare(new Shell(), new Insertion(), 1000, 100);
        compare(new Shell(), new Insertion(), 10000, 100);
    }

    private void compare(AbstractSortTemplate alg1, AbstractSortTemplate alg2, int size, int repeat) {
        double t1 = timeRamdomInput(alg1, size,repeat);
        double t2 = timeRamdomInput(alg2, size,repeat);
        System.out.println("Example size:" + size);
        System.out.println(String.format("Alg1 %s is %s times faster than Alg2 %s", alg1.name(), t2/t1, alg2.name()));
    }

    private double timeRamdomInput(AbstractSortTemplate alg, int size, int repeat) {
        double total = 0D;
        Random random = new Random(10);
        for (int t = 0; t < repeat; t++) {
            Double[] a = new Double[size];
            for (int i = 0; i < size; i++) {
                a[i] = random.nextDouble();
            }
            total += time(alg, a);
        }
        System.out.println(alg.name() + ":" + total);
        return total;
    }

    private double time(AbstractSortTemplate alg, Comparable[] a) {
        long startTime = System.currentTimeMillis();
        alg.sort(a);
        long duration = System.currentTimeMillis() - startTime;
        if (!alg.isSorted(a)) {
            throw new RuntimeException("sort error!");
        }
        return duration;
    }

}
