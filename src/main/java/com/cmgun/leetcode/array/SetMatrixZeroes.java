package com.cmgun.leetcode.array;


/**
 * 73. Set Matrix Zeroes
 * https://leetcode.com/problems/set-matrix-zeroes/
 *
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 *
 * Example 1:
 *
 * Input:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * Output:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * Example 2:
 *
 * Input:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * Output:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 *
 * Follow up:
 *
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 *
 * Created by cmgun on 2019/12/12
 */
public class SetMatrixZeroes {

    /**
     * Runtime: 1 ms, faster than 99.83% of Java online submissions for Set Matrix Zeroes.
     * Memory Usage: 42.1 MB, less than 97.14% of Java online submissions for Set Matrix Zeroes.
     * @param args
     */
    public static void main(String[] args) {
        int[][] a1 = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}

        };
        setZeroes(a1);
        print(a1);

        int[][] a2 = new int[][]{
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}

        };
        setZeroes(a2);
        print(a2);

        int[][] a3 = new int[][]{
                {1},
                {0}

        };
        setZeroes(a3);
        print(a3);
    }

    /**
     * 1. 先检查第1行、第1列是否有0元素，如果有，后续需要对第1行/列，全部置0；
     * 2. 从第2行开始，检查每一列是否有0元素，如果有，则第1行、第1列对应位置需要置0标记；
     * 3. 遍历结束后，针对第1行的每一列进行0标记将对应列全部置0；
     *
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        // 处理边界
        if (matrix == null || matrix.length == 0) {
            return;
        }
        // 行、列长度
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 首行是否有0元素
        boolean firstRowZero = false;

        // 检查第一行是否需要全部置为0，并标记有0的列和行
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    // 设置首行是否全部置为0的标记
                    if (i == 0 && !firstRowZero) {
                        firstRowZero = true;
                    }

                    // 设置0标记
                    if (i != 0) {
                        matrix[0][j] = 0;
                        matrix[i][0] = 0;
                    }
                }
            }
        }
        // 根据首行/列标记，置0操作
        for (int i = 1; i < rows; i++) {
            // 因为首列也作为标志位，所以从最后一列开始遍历，最后处理标志位
            for (int j = cols - 1; j >= 0; j--) {
                if (matrix[0][j]== 0 || matrix[i][0] == 0) {
                    // 置0
                    matrix[i][j] = 0;
                }
            }
        }
        // 设置首行标记为0
        if (firstRowZero) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    public static void print(int[][] matrix) {
        System.out.println("--matrix--");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
    }
}
