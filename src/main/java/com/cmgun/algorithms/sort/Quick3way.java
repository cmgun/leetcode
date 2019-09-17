package com.cmgun.algorithms.sort;

/**
 * 快速排序，三向切分版
 *
 * 核心思想：维护3个指针，lt:小于v，i:等于v，gt:大于v。
 * 当a[i]小于v，交换a[lt]和a[i]，lt,i各自+1；
 * 当a[i]大于v，交换a[gt]和a[i]，gt-1;
 * 啊【i】等于v，i+1
 *
 * 三切分最坏情况是，所有元素都不同
 *
 * Created by cmgun on 2019/9/18
 */
public class Quick3way extends Quick {

    @Override
    protected void sort(Comparable[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        // 定义三个指针
        int lt = start;
        int i = start + 1;
        int gt = end;
        Comparable v = a[start];
        while (i <= gt) {
            int compareResult = a[i].compareTo(v);
            if (compareResult < 0) {
                // 小于
                swap(a, lt++, i++);
            } else  if (compareResult > 0) {
                // 大于
                swap(a, gt--, i);
            } else {
                // 等于
                i++;
            }
        }
        // 切分结束，只需要排序 [start, lt-1] 和 [gt + 1, end]
        sort(a, start, lt - 1);
        sort(a, gt + 1, end);
    }
}
