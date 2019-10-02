package com.cmgun.algorithms.search;

/**
 * 二叉查找树（BST）
 *
 * 最坏情况：key分布不均匀，key顺序加入，与快排的最坏情况类似。
 *
 * 比二分查找效率更高，尽管查找命中的成本高，但插入操作和查找未命中的成本低。
 * BST能保持键的有序性。
 *
 *
 * Created by cmgun on 2019/10/1
 */
public class BinarySortTree<Key extends Comparable, Value> {

    /**
     * 根节点
     */
    private Node root;

    /**
     * 节点定义
     */
    private class Node {

        /**
         * 键
         */
        private Key key;

        /**
         * 值
         */
        private Value value;

        /**
         * 左节点
         */
        private Node left;

        /**
         * 右节点
         */
        private Node right;

        /**
         * 以该节点为根节点的子树中节点的总数量
         */
        private int nodeNums;

        public Node(Key key, Value value, int nodeNums) {
            this.key = key;
            this.value = value;
            this.nodeNums = nodeNums;
        }
    }

    /**
     * 树的大小
     * @return
     */
    public int size() {
        return size(root);
    }

    /**
     * 指定节点的子树节点数量
     * @param x
     * @return
     */
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.nodeNums;
    }


    /**
     * 获取key对应的Value
     *
     * @param key
     * @return
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 在以x为根节点的子树中查找并返回key对应的值
     *
     * @param x
     * @param key
     * @return
     */
    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        // 与x比较的结果
        int compareResult = key.compareTo(x.key);
        if (compareResult > 0) {
            // key 大于 x，key可能位于x的右子树中
            return get(x.right, key);
        } else if (compareResult < 0) {
            // key 小于 x，key可能位于x的左子树中
            return get(x.left, key);
        }
        // 相等
        return x.value;
    }



    /**
     * 新增节点
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    /**
     * 先查找key是否存在，存在则更新value。不存在则找到key合适的位置新增
     *
     * @param x
     * @param key
     * @param value
     * @return 返回key所属的节点
     */
    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            // 新增节点，给上一层递归做节点链接
            return new Node(key, value, 1);
        }
        // 与x比较的结果
        int compareResult = key.compareTo(x.key);
        if (compareResult > 0) {
            // key 大于 x，key可能位于x的右子树中
            x.right = put(x.right, key, value);
        } else if (compareResult < 0) {
            // key 小于 x，key可能位于x的左子树中
            x.left = put(x.left, key, value);
        } else {
            // 相等
            x.value = value;
        }
        // 调整x的节点数量
        x.nodeNums = size(x.left) + size(x.right) + 1;
        return x;
    }


    /* 键的有序性相关操作 */

    /**
     * 最小值
     * @return
     */
    public Key min() {
        return min(root).key;
    }

    /**
     * 找最左边的节点
     * @param x
     * @return
     */
    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    /**
     * 最大值
     * @return
     */
    public Key max() {
        return max(root).key;
    }

    /**
     * 找最右边的节点
     * @param x
     * @return
     */
    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    /**
     * 小于等于key的节点
     *
     * @param key
     * @return
     */
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            // 没有找到合适的节点
            return null;
        }
        return x.key;
    }

    /**
     * 查找小于等于key的节点
     * @param x
     * @param key
     * @return
     */
    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int compareResult = key.compareTo(x.key);
        if (compareResult == 0) {
            // key 等于 x
            return x;
        } else if (compareResult < 0) {
            // key 小于 x，小于等于key的节点在左子树中
            return floor(x.left, key);
        }
        // key 大于 x
        Node right = floor(x.right, key);
        // 如果在右子树中没有找到小于等于key的节点，则返回x
        if (right == null) {
            return x;
        }
        // 右子树中有小于等于key的节点
        return right;
    }
}
