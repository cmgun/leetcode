package com.cmgun.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 *
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 * Created by cmgun on 2019/5/16
 */
public class TwoSum {

    public static void main(String[] args) {
        // 0,1
        System.out.println(print(twoSum(new int[]{1,2,3}, 3)));
        // 1,3
        System.out.println(print(twoSum(new int[]{1,2,3,4}, 6)));
        // 0,0
        System.out.println(print(twoSum(new int[]{1,2,3}, 11)));
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; map.put(nums[i], i++)) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
        }
        return new int[] {0, 0};
    }

    private static String print(int[] result) {
        return result[0] + "," + result[1];
    }
}
