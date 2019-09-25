package com.cmgun.algorithms.search;

/**
 * 顺序查找，基于无序链表
 *
 * Created by cmgun on 2019/9/25
 */
public class SequentialSearchST<Key,Value> {

    /**
     * 头节点
     */
    private Node first;

    /**
     * 节点定义
     */
    private class Node {
        /**
         * key
         */
        private Key key;

        /**
         * Value
         */
        private Value value;

        /**
         * 下个节点
         */
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 获取Value
     *
     * @param key
     * @return
     */
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    /**
     * 添加元素
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                // 存在key，更新value
                x.value = value;
                return;
            }
        }
        // 没有命中key，新增
        first = new Node(key, value, first);
    }
}
