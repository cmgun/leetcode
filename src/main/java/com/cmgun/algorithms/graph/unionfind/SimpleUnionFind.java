package com.cmgun.algorithms.graph.unionfind;

/**
 * 动态连通性
 * 查找图的连通分量
 * 输入一对整数p, q，判断已有图中是否已经存在p到q的连通图，如果存在则不输出p, q。
 *
 * 例子：一个计算机网络中，p和q之间是否有必要建立新的连接才能通信。
 *
 * 这里展示最简单的遍历方式进行图的连通向量查找
 *
 * Created by cmgun on 2019/9/5
 */
public class SimpleUnionFind {

    /**
     * 分量id
     */
    private int[] id;

    /**
     * 分量数量
     */
    private int count;

    /**
     * 初始化分量id数组
     * 最开始每个触点都构成一个只有它自己的分量
     *
     * @param size
     */
    public SimpleUnionFind(int size) {
        this.count = size;
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
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
        return find1(p) == find1(q);
    }

    /* version 1 穷举方式 */
    /**
     * p（0 ~ size-1）所在的分量的标识符
     *
     * @param p
     * @return
     */
    public int find1(int p) {
        return id[p];
    }

    /**
     * 在p和q之间添加一条连接
     *
     * @param p
     * @param q
     */
    public void union1(int p, int q) {
        int pId = find1(p);
        int qId = find1(q);
        if (pId == qId) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
        count--;
    }


    /* version 2 查找根节点，根节点改变指向进行连通操作
     * 可以理解为只是 version 1的改良版，面对有序的输入性能极差 */
    public int find2(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union2(int p, int q) {
        int pRootId = find2(p);
        int qRootId = find2(q);
        if (pRootId == qRootId) {
            return;
        }
        // 改变根节点
        id[pRootId] = qRootId;
        count--;
    }

}
