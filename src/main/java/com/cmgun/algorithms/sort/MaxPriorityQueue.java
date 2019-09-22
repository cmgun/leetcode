package com.cmgun.algorithms.sort;

/**
 * 优先队列，基于堆和数组实现的最大堆
 *
 * 插入元素：从最后一个位置开始上浮到合适的位置
 * 删除元素：从堆顶删除，取最后一个元素替换到堆顶，下沉到合适位置
 *
 * Created by cmgun on 2019/9/22
 */
public class MaxPriorityQueue<Key extends Comparable<Key>> extends AbstractSortTemplate {

    /**
     * 队列
     */
    private Key[] queue;

    private int size = 0;


    @Override
    public String name() {
        return "MaxPriorityQueue";
    }

    @Override
    public void sort(Comparable[] a) {
        queue = (Key[]) new Comparable[a.length + 1];
        Comparable[] tmp = new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            insert((Key) a[i]);
        }
        int i = 0;
        while (!isEmpty()) {
            tmp[i++] = delMax();
        }
        for (int j = tmp.length - 1, k = 0; j >= 0; j--, k++) {
            a[k] = tmp[j];
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 插入元素
     * @param v
     */
    public void insert(Key v) {
        queue[++size] = v;
        swim(size);
    }

    public Key delMax() {
        Key max = queue[1];
        // 最后一个元素替换，下沉
        swap(queue, 1, size--);
        queue[size + 1] = null;
        sink(1);
        return max;
    }


    /**
     * 元素上浮，当插入元素比父节点大则需要上浮
     * @param k
     */
    private void swim(int k) {
        while (k > 1 && less(queue[k/2], queue[k])) {
            swap(queue, k/2, k);
            k = k / 2;
        }
    }

    /**
     * 元素下沉，删除元素时最后一位元素替换堆顶元素后需要下沉
     * @param k
     */
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            // 找出两个子节点中较大的一个
            if (j < size && less(queue[j],  queue[j + 1])) {
                j++;
            }
            // 父节点较大，不用再下沉
            if (!less(queue[k], queue[j])) {
                break;
            }
            swap(queue, k, j);
            k = j;
        }
    }
}
