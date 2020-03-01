package com.cmgun.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 94. Binary Tree Inorder Traversal
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal extends BaseTree {


    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Inorder Traversal.
     * Memory Usage: 38.4 MB, less than 5.11% of Java online submissions for Binary Tree Inorder Traversal.
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode treeNode1 = initTree(new Integer[]{1,null,2,3});
        System.out.println(inorderTraversal(treeNode1));
    }

    /**
     * 中序遍历：左→根→右
     * 递归处理
     *
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        // 空节点
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        traversal(result, root);
        return result;
    }

    /**
     * 遍历节点，添加到当前结果中
     *
     * @param result
     * @param current
     */
    private static void traversal(List<Integer> result, TreeNode current) {
        // 当前节点为空，返回
        if (current == null) {
            return;
        }
        // 左子树遍历
        traversal(result, current.left);
        // 当前节点
        result.add(current.val);
        // 右子树遍历
        traversal(result, current.right);
    }

}
