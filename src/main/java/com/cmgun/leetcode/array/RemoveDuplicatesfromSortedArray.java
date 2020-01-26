package com.cmgun.leetcode.array;

/**
 * 26. Remove Duplicates from Sorted Array
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * Created by cmgun on 2020/1/26
 */
public class RemoveDuplicatesfromSortedArray {

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array.
     * Memory Usage: 41.3 MB, less than 21.81% of Java online submissions for Remove Duplicates from Sorted Array.
     * @param args
     */
    public static void main(String[] args) {
        // 2
        System.out.println(removeDuplicates(new int[]{1,1,2}));
        // 5
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }

    /**
     * 只记录非重复的数量，需要改变原有数组的内容
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        // 处理边界
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        // 实际去重后的数组长度
        int actualLength = 1;
        int lastNums = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (lastNums != nums[i]) {
                // 和上一个不一样，则替换，同时长度+1
                lastNums = nums[i];
                // 不复制新数组，直接替换原有的元素
                nums[actualLength] = nums[i];
                actualLength++;
            }
        }
        return actualLength;

    }
}
