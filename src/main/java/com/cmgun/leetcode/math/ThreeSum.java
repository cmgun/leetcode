package com.cmgun.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum
 * https://leetcode.com/problems/3sum/
 *
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * Created by cmgun on 2019/6/4
 */
public class ThreeSum {

    /**
     * Result:
     * Runtime: 32 ms, faster than 72.37% of Java online submissions for 3Sum.
     * Memory Usage: 46.2 MB, less than 95.13% of Java online submissions for 3Sum.
     */
    public static void main(String[] args) {
        // [-1, 0, 1],[-1, -1, 2]
        List<List<Integer>> results = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for (List<Integer> result : results) {
            System.out.println(result);
        }
    }

    /**
     * break this problem into 3 parts.
     * 1. loop the nums(sorted), mark current number as i.
     * 2. sub loop starts from i + 1 to nums.length, find two point numbers
     * 3. if the sum of two point numbers = our target(-nums[i]), mark the array
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                // adjacent numbers are same, skip
                continue;
            }
            // two points of sub loop
            int start = i + 1;
            int end = nums.length - 1;
            int target = -nums[i];
            while (start < end) {
                int result = nums[start] + nums[end];
                if (result == target) {
                    // store the result
                    results.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    start++;
                    end--;
                    if (start >= end) {
                        break;
                    }
                    // skip the same number
                    while (start < end && nums[start] == nums[start - 1]) {
                        start++;
                    }
                    while (start < end && nums[end] == nums[end + 1]) {
                        end--;
                    }
                } else if (result > target) {
                    // move the max point
                    end--;
                } else {
                    // move the min point
                    start++;
                }
            }
        }
        return results;
    }
}
