package com.cmgun.leetcode.array;

/**
 * 4. Median of Two Sorted Arrays
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * Created by cmgun on 2019/5/17
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        // 2.0
        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
        // 2.5
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
    }

    /**
     * the target is to find out the number in position (nums1.length + nums2.length) / 2.
     * two cluster of num1 and nums2, compare num1[cluster] and num2[cluster], stores the minimum one into a MID number variable.
     *
     * if traversal numbers = (nums1.length + nums2.length) / 2,
     * then last MID is the median number((nums1.length + nums2.length) / 2 is even).
     * if (nums1.length + nums2.length) / 2 is odd, should stores previous number of MID, the median number = (preMID + MID) / 2.
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // nums1 cluster
        int cluster1 = 0;
        // nums2 cluster
        int cluster2 = 0;
        // mid number
        int previousMid = 0;
        int mid = 0;
        for (int i = 0; i <= (nums1.length + nums2.length) / 2; i++) {
            previousMid = mid;
            if (cluster1 == nums1.length) {
                // traversal of num1 is finish
                mid = nums2[cluster2];
                cluster2++;
            } else if (cluster2 == nums2.length) {
                mid = nums1[cluster1];
                cluster1++;
            } else if (nums1[cluster1] < nums2[cluster2]) {
                mid = nums1[cluster1];
                cluster1++;
            } else {
                mid = nums2[cluster2];
                cluster2++;
            }
        }

        // length is even
        if ((nums1.length + nums2.length) % 2 == 0) {
            return (double) (previousMid + mid) / 2;
        }
        return mid;
    }
}
