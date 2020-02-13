package com.cmgun.leetcode.array;


/**
 * 334. Increasing Triplet Subsequence
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 *
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 *
 * Formally the function should:
 *
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
 *
 * Example 1:
 *
 * Input: [1,2,3,4,5]
 * Output: true
 * Example 2:
 *
 * Input: [5,4,3,2,1]
 * Output: false
 *
 * Created by cmgun on 2020/2/12
 */
public class IncreasingTripletSubsequence {

    /**
     * Runtime: 0 ms
     * Memory Usage: 39.2 MB
     * @param args
     */
    public static void main(String[] args) {
        // true
        System.out.println(increasingTriplet(new int[]{1,2,3,4,5}));
        // false
        System.out.println(increasingTriplet(new int[]{5,4,3,2,1}));
        // true
        System.out.println(increasingTriplet(new int[]{4,5,3,2,1,7}));
        // false
        System.out.println(increasingTriplet(new int[]{4,3,2,1,7}));
        // 1,1,-2,6 false
        System.out.println(increasingTriplet(new int[]{1,1,-2,6}));

    }

    /**
     * 子串的长度记录为k，维护k-1个小元素标记；
     * 用k=3做例子（题目）：
     * 标记变量：min1，min2
     * 遍历数组，如 current <= min1，替换min1，下一个元素；
     * 如current < min2，替换min2，下个元素；
     * 直到遍历到 current > min2，结束。
     * 实际上minK有顺序关系，赋值次数相同的min[1...K]，是递增的；
     *
     * expand：如需要记录递增的子数组，可以使用额外空间记录min[1...K]最后一次赋值次数相同的层级内容
     */
    public static boolean increasingTriplet(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int current : nums) {
            if (current <= min1) {
                min1 = current;
            } else if (current < min2) {
                min2 = current;
            } else if (current > min2) {
                // 找到递增子序列
                return true;
            }
        }
        return false;
    }
}
