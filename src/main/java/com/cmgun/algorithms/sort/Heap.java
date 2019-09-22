package com.cmgun.algorithms.sort;

/**
 * 堆排序
 *
 * 核心思想：构造最大堆，堆顶元素与最后一个元素替换，再对替换元素进行元素下沉（堆调整）
 *
 * 插入元素：从最后一个位置开始上浮到合适的位置
 * 删除元素：从堆顶删除，取最后一个元素替换到堆顶，下沉到合适位置
 *
 * Created by cmgun on 2019/9/22
 */
public class Heap extends AbstractSortTemplate {



    @Override
    public String name() {
        return "Heap";
    }

    @Override
    public void sort(Comparable[] a) {
        int size = a.length;
        // 堆构造
        for (int k = size / 2 - 1; k >= 0; k--) {
            // 将元素下沉到合适位置
            sink(a, k, size);
        }
        // 下沉排序
        // 每次将堆顶元素（最大元素）跟堆底最后一个元素替换
        // 再对剩余的元素进行堆调整（堆底替换上来的元素下沉）
        for (int i = a.length - 1; i >= 1; i--) {
            swap(a, 0, i);
            sink(a, 0, i);
        }
    }


    /**
     * 元素上浮
     * @param k
     */
    private void swim(Comparable[] a, int k) {
        while (k > 1 && less(a[k/2], a[k])) {
            swap(a, k/2, k);
            k = k / 2;
        }
    }

    /**
     * 元素下沉
     */
    private void sink(Comparable[] a, int k, int subSize) {
        int index = 2 * k + 1;
        while (index < subSize) {
            // 找出两个子节点中较大的一个
            if (index + 1 < subSize && less(a[index],  a[index + 1])) {
                index++;
            }
            // 父节点较大，不用再下沉
            if (!less(a[k], a[index])) {
                break;
            }
            swap(a, k, index);
            k = index;
            index = 2 * k + 1;
        }
    }
}
