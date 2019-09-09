package com.cmgun.algorithms.sort;

/**
 * 选择排序
 *
 * 找到最小的元素，与第一个位置的元素替换；
 * 然后在剩下的元素里找第二小元素，与第二个位置的元素替换，循环这个过程直到结束。
 *
 * Created by cmgun on 2019/9/9
 */
public class Selection extends AbstractSortTemplate {

    @Override
    public void sort(Comparable[] a) {
        int size = a.length;
        for (int i = 0; i < size; i++) {
            // 当前最小元素的位置
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (less(a[j], a[min])) {
                    // 找到当前最小元素位置
                    min = j;
                }
            }
            // 替换 min 和 i
            swap(a, i, min);
        }
    }
}
