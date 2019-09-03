package com.cmgun.algorithms.queue;

import java.util.Iterator;

/**
 * 先进先出队列
 *
 * Created by cmgun on 2019/9/3
 */
public class Queue<T> implements Iterable<T> {

    /**
     * 队头，最早添加的节点
     */
    private Node first;

    /**
     * 队尾，最晚添加的节点
     */
    private Node last;

    /**
     * 元素大小
     */
    private int size;


    /**
     * 节点
     */
    private class Node {
        T item;
        Node next;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 入队
     * @param item
     */
    public void enqueue(T item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        // 队列为空
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    /**
     * 出队
     * @return
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空，无法删除元素");
        }
        T item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }


    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    /**
     * 先进先出的迭代
     */
    private class ListIterator implements Iterator<T> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("不支持操作");
        }
    }
}
