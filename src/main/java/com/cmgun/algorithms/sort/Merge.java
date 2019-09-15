package com.cmgun.algorithms.sort;

/**
 * 归并排序，自顶向下
 *
 * 将数组分成两半，各自排序后再合并起来。递归循环直到子数组大小为0，归并所有结果，排序结束。
 * 该算法需要使用辅助数组进行归并操作，当归并元素多时，辅助数组也会占较大空间。
 *
 * Created by cmgun on 2019/9/11
 */
public class Merge extends AbstractSortTemplate {

    /**
     * 辅助数组
     */
    protected Comparable[] aux;

    @Override
    public String name() {
        return "Merge";
    }

    @Override
    public void sort(Comparable[] a) {
        // 预先分配空间
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
        // 结束后释放空间
        aux = null;
    }

    /**
     * 递归排序，分组
     *
     * @param a
     * @param start
     * @param end
     */
    private void sort(Comparable[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        // 左边排序
        sort(a, start, mid);
        // 右边排序
        sort(a, mid + 1, end);
        // 归并操作
        merge(a, start, mid, end);
    }

    /**
     * 归并操作，将a[start...mid] 和 a[mid+1...end] 归并
     *
     * @param a
     * @param start
     * @param mid
     * @param end
     */
    protected void merge(Comparable[] a, int start, int mid, int end) {
        // 左边数组的起点
        int i = start;
        // 右边数组的起点
        int j = mid + 1;
        // 复制元素
        for (int k = start; k <= end; k++) {
            aux[k] = a[k];
        }
        // 归并操作
        for (int k = start; k <= end; k++) {
            if (i > mid) {
                // 右半部分有剩余元素
                a[k] = aux[j++];
            } else if (j > end) {
                // 左半部分有剩余元素
                a[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                // 两边都有未排序的元素，比较大小
                // 左半部分较小
                a[k] = aux[i++];
            } else {
                // 右半部分较小
                a[k] = aux[j++];
            }
        }
    }
}
