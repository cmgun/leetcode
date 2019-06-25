package com.cmgun.leetcode.math;

import java.util.Arrays;

/**
 * 16. 3Sum Closest
 * https://leetcode.com/problems/3sum-closest/
 *
 * Created by cmgun on 2019/6/25
 */
public class ThreeSumClosest {

    /**
     * Runtime: 5 ms, faster than 67.70% of Java online submissions for 3Sum Closest.
     * Memory Usage: 36 MB, less than 99.98% of Java online submissions for 3Sum Closest.
     */
    public static void main(String[] args) {
        // 2
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        // -2
        System.out.println(threeSumClosest(new int[]{-3,-2,-5,3,-4}, -1));
        // 0
        System.out.println(threeSumClosest(new int[]{0,2,1,-3}, 1));
    }

    /**
     * refer to 3sum
     */
    public static int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                // adjacent numbers are same, skip
                continue;
            }
            // two points of sub loop
            int end = nums.length - 1;
            int start = i + 1;
            int current = nums[i];
            while (start < end) {
                int diff = nums[start] + nums[end] + current - target;
                if (diff == 0) {
                    // find the same target, end
                    return target;
                }
                if (Math.abs(diff) < Math.abs(minDiff)) {
                    minDiff = diff;

                }
                // move start or end flag
                if (diff > 0) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return minDiff + target;
    }
}
