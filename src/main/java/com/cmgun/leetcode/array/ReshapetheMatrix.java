package com.cmgun.leetcode.array;

/**
 * 566. Reshape the Matrix
 * https://leetcode-cn.com/problems/reshape-the-matrix/
 *
 * Created by cmgun on 2022/4/2
 */
public class ReshapetheMatrix {

    public static void main(String[] args) {
        // [[1,2,3,4]]
        int[][] res1 = matrixReshape(new int[][]{{1,2},{3,4}}, 1, 4);
        print(res1);
        // [[1,2],[3,4]]
        int[][] res2 = matrixReshape(new int[][]{{1,2},{3,4}}, 2, 4);
        print(res2);
    }

    private static void print(int[][] mat) {
        for (int[] row : mat) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int oriCount = mat.length * mat[0].length;
        if (oriCount != r * c) {
            return mat;
        }
        int[][] reshape = new int[r][c];
        int rowNum = 0, colNum = 0;
        for (int[] row : mat) {
            int j = 0;
            while (colNum < c && j < row.length) {
                reshape[rowNum][colNum] = row[j];
                colNum++;
                if (colNum >= c) {
                    rowNum++;
                    colNum = 0;
                }
                j++;
            }
        }
        return reshape;
    }

}
