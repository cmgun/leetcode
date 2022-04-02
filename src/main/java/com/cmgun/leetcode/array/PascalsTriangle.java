package com.cmgun.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. Pascal's Triangle
 * https://leetcode-cn.com/problems/pascals-triangle/
 *
 * Created by cmgun on 2022/4/2
 */
public class PascalsTriangle {

    public static void main(String[] args) {
        List<List<Integer>> result1 = generate(5);
        print(result1);
    }

    private static void print(List<List<Integer>> result) {
        for (List<Integer> list : result) {
            for (Integer x : list) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }


    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    row.add(1);
                } else if (j == i - 1) {
                    row.add(1);
                } else if (i > 1) {
                    List<Integer> lastRow = result.get(i - 2);
                    int sum = lastRow.get(j - 1) + lastRow.get(j);
                    row.add(sum);
                }
            }
            result.add(row);
        }
        return result;
    }
}
