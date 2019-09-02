package com.cmgun.algorithms.stack;

import java.util.Iterator;

/**
 * 下压栈（LIFO） 后进先出
 * 可自动扩容缩容，性能受当前栈元素数量影响，数量越多，扩/缩容越慢
 *
 * Created by cmgun on 2019/9/2
 */
public class ResizingArrayStack<T> implements Iterable<T> {

    /**
     * 栈元素
     */
    private T[] a = (T[]) new Object[1];

    /**
     * 栈元素数量
     */
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 扩容/缩容
     * @param max
     */
    private void resize(int max) {
        System.out.println("resize, max:" + max);
        T[] tmp = (T[]) new Object[max];
        for (int i = 0; i < size; i++) {
            tmp[i] = a[i];
        }
        a = tmp;
    }

    /**
     * 新加元素到栈顶
     * @param t
     */
    public void push(T t) {
        if (size == a.length) {
            resize(2 * a.length);
        }
        a[size++] = t;
    }

    /**
     * 从栈顶删除元素
     * @return
     */
    public T pop() {
        T t = a[--size];
        // help gc
        a[size] = null;
        if (size > 0 && size == a.length / 4) {
            resize(a.length / 2);
        }
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * 后进先出的迭代
     */
    private class ReverseArrayIterator implements Iterator<T> {

        private int i = size;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return a[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("不支持操作");
        }
    }
}
