package com.cmgun.algorithms.search;

/**
 * 二分查找，基于有序数组
 *
 * Created by cmgun on 2019/9/28
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;

    private Value[] values;

    private int size;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    /**
     * 当前元素大小
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回表中小于等于给定Key的键的数量
     * 二分查找
     *
     * @param key
     * @return
     */
    public int rank(Key key) {
        // 起始位置和结束位置
        int start = 0;
        int end = size - 1;
        while (start <= end) {
            // 中间点
            int mid = start + (end - start) / 2;
            // 与中间点比较结果
            int result = key.compareTo(keys[mid]);
            if (result > 0) {
                // key比较大
                start = mid + 1;
            } else if (result < 0) {
                // key比较小
                end = mid - 1;
            } else {
                // key与当前mid相等
                return mid;
            }
        }
        // 无法查找到key，则返回start位置
        return start;
    }

    /**
     * 获取key对应的value
     * @param key
     * @return
     */
    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        // 小于等于key的位置
        int i = rank(key);
        // 如果key一致则返回value
        if (i < size && keys[i].compareTo(key) == 0) {
            return values[i];
        }
        return null;
    }

    /**
     * 新增键值对
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        // 查找键，如果存在则更新
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        // i为新增key应该放置的位置，i后的元素后移一位
        for (int j = size; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        size++;
    }

}
