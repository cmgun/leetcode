package com.cmgun.algorithms.graph.unionfind;

/**
 * 根据SimpleUnionFind的方式，version 2，进行改良
 *
 * 连通union操作时，永远将小树的根节点连接到大树底下，减少find的遍历层级
 *
 * Created by cmgun on 2019/9/6
 */
public class WeightedQuickUnionFind {

    /**
     * 分量id
     */
    private int[] id;

    /**
     * 分量数量
     */
    private int count;

    /**
     * 各个根节点所对应的分量权重（大小）
     */
    private int[] sz;

    /**
     * 初始化分量id数组
     * 最开始每个触点都构成一个只有它自己的分量
     *
     * @param size
     */
    public WeightedQuickUnionFind(int size) {
        this.count = size;
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            sz[i] = 1;
        }
    }

    /**
     * 当前连通分量的数量
     *
     * @return
     */
    public int count() {
        return count;
    }

    /**
     * 如果p和q存在于同一个分量中则返回true
     *
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        // 两个触点所在的分量标识符相同
        return find(p) == find(q);
    }

    /**
     * p（0 ~ size-1）所在的分量的标识符
     *
     * @param p
     * @return
     */
    public int find(int p) {
        // 一直找到根节点
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    /**
     * 在p和q之间添加一条连接
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) {
            return;
        }

        // 将小树的根节点连接到大树的根节点
        if (sz[pId] < sz[qId]) {
            id[pId] = qId;
            sz[qId] += sz[pId];
        } else {
            id[qId] = pId;
            sz[pId] += sz[qId];
        }
        count--;
    }
}
