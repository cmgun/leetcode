package com.cmgun.leetcode.array;

/**
 * 11. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Created by cmgun on 2019/5/17
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        // 49
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    /**
     * two point search
     */
    public static int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        // search begin in two point
        while (left <= right) {
            int x = right - left;
            int y = Math.min(height[left], height[right]);
            int currentArea = x * y;
            if (currentArea > maxArea) {
                maxArea = currentArea;
            }
            // the lower one needs to move to adjacent position
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
