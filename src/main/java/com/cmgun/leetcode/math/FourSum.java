package com.cmgun.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 4Sum
 * https://leetcode.com/problems/4sum/
 *
 * Created by cmgun on 2019/12/2
 */
public class FourSum {

    /**
     * Runtime: 25 ms, faster than 50.43% of Java online submissions for 4Sum.
     * Memory Usage: 37.4 MB, less than 100.00% of Java online submissions for 4Sum.
     *
     * @param args
     */
    public static void main(String[] args) {
        // [-2, -1, 1, 2],[-2, 0, 0, 2],[-1, 0, 0, 1]
        List<List<Integer>> results = fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        for (List<Integer> result : results) {
            System.out.println(result);
        }

        // [0,0,0,0]
        List<List<Integer>> results1 = fourSum(new int[]{0,0,0,0}, 0);
        for (List<Integer> result : results1) {
            System.out.println(result);
        }
    }

    /**
     * base on 3Sum
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        for (int a = 0; a < nums.length - 3; a++) {
            // same number
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            // 3Sum
            for (int j = a + 1; j < nums.length - 2; j++) {
                if (j > a + 1 && nums[j] == nums[j - 1]) {
                    // adjacent numbers are same, skip
                    continue;
                }
                // two points of sub loop
                int start = j + 1;
                int end = nums.length - 1;
                while (start < end) {
                    int result = nums[a] + nums[j] + nums[start] + nums[end];
                    if (result == target) {
                        // store the result
                        results.add(Arrays.asList(nums[a], nums[j], nums[start], nums[end]));
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
        }
        return results;
    }

}
