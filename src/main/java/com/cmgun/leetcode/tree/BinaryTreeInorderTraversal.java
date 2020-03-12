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
        // 传统递归
        TreeNode treeNode1 = initTree(new Integer[]{1,null,2,3});
        System.out.println(inorderTraversal(treeNode1));
        // Morris Traversal
        TreeNode treeNode2 = initTree(new Integer[]{1,null,2,3});
        System.out.println(inorderTraversal1(treeNode2));
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


    /**
     * 使用Morris Traversal
     * 用线索二叉树进行遍历，步骤：
     * 1、初始化当前节点 cur 为 root 节点.
     * 2、如果 cur 没有左孩子，则输出当前节点并将其右孩子作为当前节点,即 cur = cur->right。
     * 3、如果 cur 有左孩子，在当前节点 cur 的左子树中找到当前节点 cur 在中序遍历下的前驱节点。
     * a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
     * b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
     * 4、重复以上2、3直到当前节点为空。
     *
     * 时间复杂度O(N)，空间复杂度O(1)；传统递归需要空间复杂度为O(N)
     */
    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }

}
