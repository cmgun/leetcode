package com.cmgun.algorithms.search;

/**
 * 基于拉链法的散列表
 * 拉链法：发生散列碰撞的时候，将冲突的元素存放在链表中
 *
 * Created by cmgun on 2019/11/1
 */
public class SeparateChainingHashST<Key, Value> {

    /**
     * 键值对的大小
     */
    private int keySize;

    /**
     * 散列表大小
     */
    private int hashSize;

    /**
     * 散列碰撞链表
     */
    private SequentialSearchST<Key, Value>[] collisionList;

    public SeparateChainingHashST() {
        this(997);
    }


    public SeparateChainingHashST(int hashSize) {
        this.hashSize = hashSize;
        // 创建 hashSize 条链表
        collisionList = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[hashSize];
        for (int i = 0; i < hashSize; i++) {
            collisionList[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key) {
        // hashCode 屏蔽符号位，然后除留余数法计算除以hashSize的余数
        return (key.hashCode() & 0x7fffffff ) % hashSize;
    }

    public Value get(Key key) {
        return (Value) collisionList[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        collisionList[hash(key)].put(key, value);
    }
}
