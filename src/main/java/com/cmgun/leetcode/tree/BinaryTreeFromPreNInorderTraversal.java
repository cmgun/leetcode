package com.cmgun.leetcode.tree;

import java.util.Arrays;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class BinaryTreeFromPreNInorderTraversal extends BaseTree {


    /**
     * Runtime: 5 ms, faster than 14.96% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
     * Memory Usage: 40.3 MB, less than 13.08% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] preorder1 = new int[]{3,9,20,15,7};
        int[] inorder1 = new int[]{9,3,15,20,7};
        // v1版本
        TreeNode node1 = buildTreeV1(preorder1, inorder1);
        printTree(node1);
        // v2优化
        TreeNode node2 = buildTreeV2(preorder1, inorder1);
        System.out.println();
        printTree(node2);
    }


    /**
     * 前序遍历：根→左→右
     * 中序遍历：左→根→右
     *
     * 1、前序遍历确定根节点，从中序遍历中找到该根节点，中序遍历中该节点前为左子树，后为右子树；
     * 2、对拆分后的左右子树继续进行 step1 的遍历，前序拆分：根据中序遍历左右子树的长度截取；
     * 3、当中序遍历的节点只剩下一个之后，该节点为叶子节点
     */
    public static TreeNode buildTreeV1(int[] preorder, int[] inorder) {
        // 长度非法
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        // 当前树的根节点
        TreeNode root = new TreeNode(preorder[0]);
        // 当前根节点在中序遍历中的位置
        int inorderIndex = getIndex(root.val, inorder);
        if (inorderIndex < 0) {
            throw new RuntimeException("illegal inorder index");
        }
        // 如果前序遍历剩下一个节点，则已经遍历到叶子节点
        if (preorder.length == 1) {
            return root;
        }
        // 拆分左右子树
        // 中序遍历的左右子树
        int[] inorderLeft = Arrays.copyOfRange(inorder, 0, inorderIndex);
        int[] inorderRight = Arrays.copyOfRange(inorder, inorderIndex + 1, inorder.length);
        // 前序遍历的左右子树
        int[] preorderLeft = Arrays.copyOfRange(preorder, 1, inorderLeft.length + 1);
        int[] preorderRight = Arrays.copyOfRange(preorder, inorderLeft.length + 1, preorder.length);

        // 左右子树关系构建
        if (inorderLeft.length > 0) {
            root.left = buildTreeV1(preorderLeft, inorderLeft);
        }
        if (inorderRight.length > 0) {
            root.right = buildTreeV1(preorderRight, inorderRight);
        }
        return root;
    }

    /**
     * 找到目标元素在数组中的位置
     * @param target
     * @param array
     * @return
     */
    public static int getIndex(int target, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }


    private static int inIndex = 0;
    private static int preIndex = 0;

    /**
     * 实际上只要顺序遍历前序遍历，一次遍历就能完成树的构建
     *
     */
    public static TreeNode buildTreeV2(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, Integer.MIN_VALUE);
    }

    private static TreeNode buildTree(int[] preorder, int[] inorder, int byValue) {
        // 前序遍历遍历结束
        if (preIndex >= preorder.length) {
            return null;
        }
        // 当前子树的中序遍历遍历结束
        if (inorder[inIndex] == byValue) {
            inIndex++;
            return null;
        }
        // 当前根节点
        TreeNode node = new TreeNode(preorder[preIndex++]);
        node.left = buildTree(preorder, inorder, node.val);
        node.right = buildTree(preorder, inorder, byValue);
        return node;
    }

}
