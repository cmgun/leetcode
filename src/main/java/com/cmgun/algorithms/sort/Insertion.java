package com.cmgun.algorithms.sort;

/**
 * 插入排序
 *
 * 每次给当前元素找到它的最终位置，即插入到它左边的有序数组中。
 * 从第一个元素开始，第一个元素的位置就是它原本的位置。
 * 第二个元素插入到它左边的有序数组中，并且插入位置就是它在该有序子数组中的位置。
 * 重复直到所有元素都排序完毕
 *
 * 与选择排序对比，在数组大部分有序的情况下，性能会较选择排序更优
 * 时间复杂度：O(N^2)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 *
 * Created by cmgun on 2019/9/9
 */
public class Insertion extends AbstractSortTemplate {

    @Override
    public String name() {
        return "Insertion";
    }

    @Override
    public void sort(Comparable[] a) {
        int size = a.length;
        for (int i = 1; i < size; i++) {
            // 将当前元素插入到前 i-2 长度的子数组中
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                swap(a, j, j - 1);
            }
        }
    }
}
