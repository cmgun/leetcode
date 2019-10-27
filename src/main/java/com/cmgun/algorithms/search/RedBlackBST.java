package com.cmgun.algorithms.search;

/**
 * 红黑树
 *
 * 定义：
 * 1.红链接均为左链接；
 * 2.没有任何一个节点同时和两条红链接相连；
 * 3.该树是完美的黑色平衡，即任意空链接到根节点的路径上的黑链接数量相同。
 *
 * 由2-3树变形得，即由标准二叉查找树+额外信息表示2-3树。（2-3树是一个节点最多可以有2个值，3个分支）
 * 【红链接】将两个2-节点连接起来构成一个3-节点，【黑链接】表示2-3树中的普通链接
 * 如果把红链接画平，那就是一个2-3树:)
 * 红黑树既有二叉查找树中简洁高效的查找方法、又有2-3树中高效的平衡插入算法
 *
 * Q：如何解决一个节点同时和两条红链接相连问题：
 * A：左、右旋后都会对链接进行染色。将相连的红链接都调整为某个n节点的左右链接，然后进行去色处理。
 *
 * Created by cmgun on 2019/10/22
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    /**
     * 红链接
     */
    private static final boolean RED = true;
    /**
     * 黑链接
     */
    private static final boolean BLACK = false;

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
        Key key;

        /**
         * 值
         */
        Value value;

        /**
         * 左链接
         */
        Node left;

        /**
         * 右链接
         */
        Node right;

        /**
         * 父节点指向该节点的链接是否染色
         */
        boolean color;

        /**
         * 子树中节点数量
         */
        int nodeNums;

        public Node(Key key, Value value, boolean color, int nodeNums) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.nodeNums = nodeNums;
        }
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
     * 判断指向该节点的链接是否红色
     * @param x
     * @return
     */
    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    /**
     * 左旋转节点h
     * 这个左旋操作，实际将h的右节点变成h的父节点，同时给这个链接染色
     *
     * @param h
     * @return 左旋后替换h的节点
     */
    private Node rotateLeft(Node h) {
        // 将h的右节点变为h的父节点
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        // 保留h原有的父节点链接颜色
        x.color = h.color;
        // h到父节点的链接为红色
        h.color = RED;
        x.nodeNums = h.nodeNums;
        h.nodeNums = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 右旋转节点h
     * 此时需要再进行一次左旋，红链接才会变成左链接
     *
     * @param h
     * @return 左旋后替换h的节点
     */
    private Node rotateRight(Node h) {
        // 将h的左节点变为h的父节点
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        // 保留h原有的父节点链接颜色
        x.color = h.color;
        // h到父节点的链接为红色
        h.color = RED;
        x.nodeNums = h.nodeNums;
        h.nodeNums = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 颜色转换，将h的父链接染色，子链接去色
     * 只要h的左右子链接都是红色，就可进行颜色转换
     *
     * @param h
     */
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    /**
     * 新增节点
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
        // 根节点的颜色反转
        root.color = BLACK;
    }

    /**
     * 找到key则更新，否则新建节点
     *
     * @param h
     * @param key
     * @param value
     * @return
     */
    private Node put(Node h, Key key,Value value) {
        if (h == null) {
            // 直接创建新节点，红链接
            return new Node(key, value, RED, 1);
        }
        int compareResult = key.compareTo(h.key);
        if (compareResult < 0) {
            // key小于当前节点
            h.left = put(h.left, key, value);
        } else if (compareResult > 0) {
            // key大于当前节点
            h.right = put(h.right, key, value);
        } else {
            // 找到key的位置，更新
            h.value = value;
        }

        // 树平衡处理、红链接处理
        // 1.右链接红色，左链接黑色。左旋处理
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        // 2.连续两个左链接都时红色，右旋处理，等待3做颜色转换
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        // 3.颜色转换
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        // 调整节点数量
        h.nodeNums = 1 + size(h.left) + size(h.right);
        return h;
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
}
