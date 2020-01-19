package com.cmgun.leetcode.tree;

/**
 * 98. Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 */
public class ValidBST {


    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Validate Binary Search Tree.
     * Memory Usage: 45.4 MB, less than 5.58% of Java online submissions for Validate Binary Search Tree.
     * @param args
     */
    public static void main(String[] args) {
        // [2,1,3]，true
        TreeNode tree1 = initTree(new Integer[]{2,1,3});
        System.out.println(isValidBST(tree1));
        // [5,1,4,null,null,3,6]，false
        TreeNode tree2 = initTree(new Integer[]{5,1,4,null,null,3,6});
        System.out.println(isValidBST(tree2));
        // [1,1]，false
        TreeNode tree3 = initTree(new Integer[]{1,1});
        System.out.println(isValidBST(tree3));
        // [10,5,15,null,null,6,20]，false
        TreeNode tree4 = initTree(new Integer[]{10,5,15,null,null,6,20});
        System.out.println(isValidBST(tree4));
    }

    private static TreeNode initTree(Integer[] nums) {
        if (nums.length == 0) {
            return null;
        }
        // 所有树节点初始化
        TreeNode[] treeNodes = new TreeNode[nums.length];
        TreeNode root = new TreeNode(nums[0]);
        treeNodes[0] = root;
        // 建立节点之间父子关系
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == null) {
                // 空节点跳过
                continue;
            }
            treeNodes[i] = new TreeNode(nums[i]);

            if (i % 2 == 0) {
                // 右节点
                // 寻找父节点
                int parentIndex = (i - 1) / 2;
                TreeNode parent = treeNodes[parentIndex];
                parent.right = treeNodes[i];
            } else {
                // 寻找父节点
                int parentIndex = i / 2;
                TreeNode parent = treeNodes[parentIndex];
                parent.left = treeNodes[i];
            }
        }
        return root;
    }

    /**
     * 校验是否二叉搜索树
     * 1.一个节点的左子树的所有keys都比该节点小
     * 2.一个节点的右子树的所有keys都比该节点大
     * 3.左右子树都是二叉搜索树
     *
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidNode(root, null, null);
    }

    /**
     * 校验当前节点范围是否合法
     * @param current
     * @param minVal
     * @param maxVal
     * @return
     */
    private static boolean isValidNode(TreeNode current, Integer minVal, Integer maxVal) {
        // 节点为空
        if (current == null) {
            return true;
        }
        // 最小边界
        if (minVal != null && current.val <= minVal) {
            return false;
        }
        // 最大边界
        if (maxVal != null && current.val >= maxVal) {
            return false;
        }
        // 节点在合法范围内
        // 检查左右子树是否合法
        if (!isValidNode(current.left, minVal, current.val)) {
            return false;
        }
        // 右子树是否合法
        if (!isValidNode(current.right, current.val, maxVal)) {
            return false;
        }
        // 左右子树都合法
        return true;
    }

    /**
     * 树节点定义
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}
