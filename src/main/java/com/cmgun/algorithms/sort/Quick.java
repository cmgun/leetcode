package com.cmgun.algorithms.sort;

/**
 * 快速排序
 *
 * 将数组分成两个子数组，各自独立排序，分治递归。
 * 与归并不同的是，归并算法里子数组排序后需要进行归并。快排则不需要，当两个子数组都有序时整体数组就有序。
 *
 * 核心思想：选择切分位置，切分前后部分各自排序。
 * 切分点的选择会影响排序效果。
 *
 * 算法改进：
 * 1、小数组时切换到插入排序
 * 2、使用子数组的一部分元素的中位数作为切分点，三取样切分，即用3作为切分点的间隔
 * 3、有大量重复元素时，数组可以切分为三部分，小于、等于、大于。
 *
 * Created by cmgun on 2019/9/15
 */
public class Quick extends AbstractSortTemplate {

    @Override
    public String name() {
        return "quick";
    }

    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    protected void sort(Comparable[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        // 切分
        int partition = partition(a, start, end);
        sort(a, start, partition - 1);
        sort(a, partition + 1, end);
    }

    /**
     * 从start开始切分数组
     */
    private int partition(Comparable[] a, int start, int end) {
        int i = start;
        int j = end + 1;
        // 切分点
        Comparable partition = a[start];
        while (true) {
            // 扫描切分点左右两边元素并交换
            while (less(a[++i], partition)) {
                if (i == end) {
                    break;
                }
            }
            while (less(partition, a[--j])) {
                if (j == start) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            // i,j互换
            swap(a, i, j);
        }
        // 切分点放到正确位置
        swap(a, start, j);
        return j;
    }


}
