package com.cmgun.leetcode.array;

/**
 * 88. Merge Sorted Array
 * https://leetcode-cn.com/problems/merge-sorted-array/
 *
 * Created by cmgun on 2022/3/31
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        // 1,2,2,3,5,6
        int[] num1 = new int[]{1,2,3,0,0,0};
        merge(num1, 3, new int[]{2,5,6}, 3);
        print(num1, 6);
        // 1
        int[] num2 = new int[]{1};
        merge(num2, 1, new int[]{}, 0);
        print(num2, 1);
        // 1
        int[] num3 = new int[]{0};
        merge(num3, 0, new int[]{1}, 1);
        print(num3, 1);
    }

    private static void print(int[] nums, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int length = m + n;
        int i = 0, j = 0, count = 0;
        int[] result = new int[length];
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                result[count++] = nums1[i];
                i++;
            } else {
                result[count++] = nums2[j];
                j++;
            }
        }
        while (i < m) {
            result[count++] = nums1[i];
            i++;
        }
        while (j < n) {
            result[count++] = nums2[j];
            j++;
        }
        System.arraycopy(result, 0, nums1, 0, length);
    }
}
