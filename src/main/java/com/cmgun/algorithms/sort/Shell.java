package com.cmgun.algorithms.sort;

/**
 * 希尔排序
 *
 * 基于插入排序实现。主要思想：待排序数组中任意间隔为h的数组都是有序的，实质是一种分组排序。
 * 先进行间隔为h的分组排序，分组有序后，h缩小一半，继续排序，直到h为1，则排序结束。
 *
 * 简单实现且性能高，在数组size不是特别大时可以优先考虑。
 *
 * Created by cmgun on 2019/9/10
 */
public class Shell extends AbstractSortTemplate {

    @Override
    public String name() {
        return "Shell";
    }

    @Override
    public void sort(Comparable[] a) {
        int size = a.length;
        int h = 1;
        // 序列为 1/2(3^k - 1)，从N/3递减到1
        // 初始化增量序列
        while (h < size / 3) {
            // 1, 4, 13, 40, ...
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // 开始一个h跨度的分组排序
            for (int i = h; i < size; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    swap(a, j, j - h);
                }
            }
            // h减少
            h = h / 3;
        }
    }
}
