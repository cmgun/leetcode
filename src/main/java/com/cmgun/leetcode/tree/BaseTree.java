package com.cmgun.leetcode.tree;

/**
 * 树相关的定义基类
 *
 * Created by cmgun on 2020/3/1
 */
public class BaseTree {

    /**
     * 树节点定义
     */
    protected static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 初始化二叉树
     * @param nums
     * @return
     */
    protected static TreeNode initTree(Integer[] nums) {
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

            // 建立父子节点关系
            if (i % 2 == 0) {
                // 右节点
                // 寻找父节点
                int parentIndex = (i - 1) / 2;
                TreeNode parent = treeNodes[parentIndex];
                // 如果父节点为空，往后找
                if (parent == null) {
                    parent = treeNodes[++parentIndex];
                }
                parent.right = treeNodes[i];
            } else {
                // 左节点
                // 寻找父节点
                int parentIndex = i / 2;
                TreeNode parent = treeNodes[parentIndex];
                if (parent == null) {
                    parent = treeNodes[++parentIndex];
                }
                parent.left = treeNodes[i];
            }
        }
        return root;
    }
}
