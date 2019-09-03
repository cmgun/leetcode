package com.cmgun.algorithms.stack;

import java.util.Iterator;

/**
 * 下压栈（LIFO） 后进先出
 * 链表实现方式
 *
 * Created by cmgun on 2019/9/3
 */
public class Stack<T> implements Iterable<T> {

    /**
     * 栈顶
     */
    private Node first;


    /**
     * 栈元素数量
     */
    private int size = 0;

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
     * 新加元素到栈顶
     * @param item
     */
    public void push(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    /**
     * 从栈顶删除元素
     * @return
     */
    public T pop() {
        T item = first.item;
        first = first.next;
        size--;
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    /**
     * 后进先出的迭代
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
