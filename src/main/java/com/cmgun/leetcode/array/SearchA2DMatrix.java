package com.cmgun.leetcode.array;

/**
 * 74. Search a 2D Matrix
 * https://leetcode.com/problems/search-a-2d-matrix/
 * <p>
 * Created by cmgun on 2020/1/23
 */
public class SearchA2DMatrix {

    /**
     * Runtime: 1 ms, faster than 9.23% of Java online submissions for Search a 2D Matrix.
     * Memory Usage: 47.7 MB, less than 6.06% of Java online submissions for Search a 2D Matrix.
     * @param args
     */
    public static void main(String[] args) {
        // matrix = [
        //  [1,   3,  5,  7],
        //  [10, 11, 16, 20],
        //  [23, 30, 34, 50]
        //]
        //target = 3，true
        System.out.println(searchMatrix(new int[][]{
                {1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}
        }, 3));
        // target=13, false
        System.out.println(searchMatrix(new int[][]{
                {1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}
        }, 13));
    }

    /**
     * 先按行的头位搜索，找到后再按列搜索
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            // 空
            return false;
        }
        // 每行的最后一列，为分界点
        int i = 0;
        int j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            // 找到节点
            if (matrix[i][j] == target) {
                return true;
            }
            // 当前节点比target小，下一行
            if (matrix[i][j] < target) {
                i++;
            } else {
                // 当前节点（每行最后一列开始）比target大，说明target在i行
                j--;
            }
        }
        return false;
    }
}
