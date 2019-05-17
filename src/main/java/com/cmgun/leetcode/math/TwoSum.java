package com.cmgun.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 *
 * Created by cmgun on 2019/5/16
 */
public class TwoSum {

    public static void main(String[] args) {
        System.out.println(print(twoSum(new int[]{1,2,3}, 3)));
        System.out.println(print(twoSum(new int[]{1,2,3,4}, 6)));
        System.out.println(print(twoSum(new int[]{1,2,3}, 11)));
    }

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
