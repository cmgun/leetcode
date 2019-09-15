package com.cmgun.algorithms.sort;

/**
 * 归并排序，自底向上
 * 先排序子集，再归并成一个大集合。
 *
 * Created by cmgun on 2019/9/15
 */
public class MergeBU extends Merge {

    @Override
    public void sort(Comparable[] a) {
        int size = a.length;
        aux = new Comparable[size];
        // 从子集大小为1开始，进行排序，2倍扩展
        for (int subSize = 1; subSize < size; subSize = subSize * 2) {
            for (int start = 0; start < size - subSize; start += subSize * 2) {
                merge(a, start, start + subSize - 1, Math.min(start + subSize * 2 - 1, size - 1));
            }
        }
        // 结束后释放空间
        aux = null;
    }
}
