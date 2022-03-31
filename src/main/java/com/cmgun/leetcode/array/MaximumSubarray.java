package com.cmgun.leetcode.array;

/**
 * 53. Maximum Subarray
 * https://leetcode-cn.com/problems/maximum-subarray/
 *
 * Created by cmgun on 2022/3/31
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        // 6
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        // 1
        System.out.println(maxSubArray(new int[]{1}));
        // 23
        System.out.println(maxSubArray(new int[]{5,4,-1,7,8}));
    }

    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int pre = 0;
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            max = Math.max(pre, max);
        }
        return max;
    }
}
