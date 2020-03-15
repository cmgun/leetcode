package com.cmgun.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal extends BaseTree {


    /**
     * Runtime: 1 ms, faster than 72.48% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
     * Memory Usage: 38.3 MB, less than 5.77% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode treeNode1 = initTree(new Integer[]{3,9,20,null,null,15,7});
        List<List<Integer>> result1 = zigzagLevelOrder(treeNode1);
        printResult(result1);

    }


    /**
     * BFS遍历，定义双向队列存储遍历元素
     * 1、从左边开始扫描，左边扫描的从左边入队；
     * 2、从左边入队的则从右边出队，反之亦然；
     * 3、本层结束后，下层遍历则转换扫描方向；
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 结果集
        List<List<Integer>> result = new ArrayList<>();
        // 处理边界
        if (root == null) {
            return result;
        }
        // 双向队列
        Deque<TreeNode> deque = new LinkedList<>();
        // 当前层最后一个节点
        TreeNode last = root;
        // 当前节点
        TreeNode cur;
        // 缓存结果
        List<Integer> buffer = new ArrayList<>();

        // 从左边开始
        boolean isLeft = true;
        // 根节点记录
        deque.offerFirst(root);

        // 双向队列非空时，则还有元素未遍历
        while (!deque.isEmpty()) {
            // 根据当前遍历方向获取当前节点
            cur = isLeft ? deque.pollFirst() : deque.pollLast();
            buffer.add(cur.val);

            // 添加下一层的节点到队列中
            if (isLeft) {
                // 当前左边扫描，下层右边扫描，则下层节点从右边入队
                if (cur.left != null) {
                    deque.offerLast(cur.left);
                }
                if (cur.right != null) {
                    deque.offerLast(cur.right);
                }
            } else {
                // 当前右边扫描，下层左边扫描，则下层节点从左边入队
                if (cur.right != null) {
                    deque.offerFirst(cur.right);
                }
                if (cur.left != null) {
                    deque.offerFirst(cur.left);
                }
            }

            // 已经遍历到最后一个节点
            if (cur == last) {
                result.add(buffer);
                // 刷新buffer
                buffer = new ArrayList<>();
                // 下层最后一个节点更新
                last = isLeft ? deque.peekFirst() : deque.peekLast();
                // 转向
                isLeft = !isLeft;
            }
        }
        return result;
    }

    private static void printResult(List<List<Integer>> result) {
        for (List<Integer> sub : result) {
            for (Integer a : sub) {
                System.out.printf(a + ",");
            }
            System.out.println();
        }
    }

}
