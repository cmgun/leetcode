package com.cmgun.leetcode.array;

/**
 * 36. Valid Sudoku
 * https://leetcode-cn.com/problems/valid-sudoku/
 *
 * Created by cmgun on 2022/4/2
 */
public class ValidSudoku {

    public static void main(String[] args) {
        // true
        System.out.println(isValidSudoku(new char[][]{{'5','3','.','.','7','.','.','.','.'}
        ,{'6','.','.','1','9','5','.','.','.'}
        ,{'.','9','8','.','.','.','.','6','.'}
        ,{'8','.','.','.','6','.','.','.','3'}
        ,{'4','.','.','8','.','3','.','.','1'}
        ,{'7','.','.','.','2','.','.','.','6'}
        ,{'.','6','.','.','.','.','2','8','.'}
        ,{'.','.','.','4','1','9','.','.','5'}
        ,{'.','.','.','.','8','.','.','7','9'}
        }));
        // false
        System.out.println(isValidSudoku(new char[][]{{'8','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}

        }));
    }

    public static boolean isValidSudoku(char[][] board) {
        // check row
        for (char[] row : board) {
            boolean[] flag = new boolean[10];
            for (char x : row) {
                if (x == '.') {
                    continue;
                }
                int index = x - '0';
                if (flag[index]) {
                    return false;
                } else {
                    flag[index] = true;
                }
            }
        }
        // check column
        for (int j = 0; j < 9; j++) {
            boolean[] flag = new boolean[10];
            for (int i = 0; i < 9; i++) {
                int x = board[i][j];
                if (x == '.') {
                    continue;
                }
                int index = x - '0';
                if (flag[index]) {
                    return false;
                } else {
                    flag[index] = true;
                }
            }
        }
        // check sub-box
        for (int subR = 0; subR < 3; subR++) {
            for (int subC = 0; subC < 3; subC++) {
                boolean[] flag = new boolean[10];
                for (int i = 0; i < 3; i++) {
                    int row = i + subR * 3;
                    for (int j = 0; j < 3; j++) {
                        int col = j + subC * 3;
                        int x = board[row][col];
                        if (x == '.') {
                            continue;
                        }
                        int index = x - '0';
                        if (flag[index]) {
                            return false;
                        } else {
                            flag[index] = true;
                        }
                    }

                }
            }
        }
        return true;
    }
}
