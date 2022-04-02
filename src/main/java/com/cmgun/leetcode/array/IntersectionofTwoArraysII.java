package com.cmgun.leetcode.array;

import java.util.Arrays;

/**
 * 350. Intersection of Two Arrays II
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 *
 * Created by cmgun on 2022/4/1
 */
public class IntersectionofTwoArraysII {

    public static void main(String[] args) {
        // 1,2,2,3,5,6
        int[] res1 = intersect(new int[]{1,2,2,1}, new int[]{2, 2});
        print(res1);
    }

    private static void print(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        quickSort(nums1, 0, nums1.length - 1);
        quickSort(nums2, 0, nums2.length - 1);
        int[] result = new int[Math.max(nums1.length, nums2.length)];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result[k++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOfRange(result, 0, k);
    }

    private static void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int left = low, right = high;
        int temp = nums[low];
        while (low < high) {
            while (temp <= nums[high] && low < high) {
                high--;
            }
            nums[low] = nums[high];
            while (nums[low] <= temp && low < high) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = temp;
        quickSort(nums, left, low - 1);
        quickSort(nums, low + 1, right);
    }

}
