package com.cmgun.lettcode.dp;

/**
 * 10. Regular Expression Matching
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * Created by cmgun on 2019/5/12
 */
public class Solution10 {

    public static void main(String[] args) {

    }

    /**
     * dynamic programming
     * dp[i][j] is a two-dimensional array which represents match result of s[i] and p[j]
     * the conditions of dp are as following:
     *
     * p[i]'s conditions:
     * 1. p[j] != '*' and p[j] != '.', if p[j] = s[i], then dp[i][j] = dp[i-1][j-1]
     * 2. p[j] = '.', dp[i][j] = dp[i-1][j-1]
     * 3. p[j] = '*', here has two inner conditions
     *   3.1. p[j-1] != s[i], dp[i][j] = dp[i][j-2]
     *   3.2. p[j-1] = s[i] or p[j-1] = '.'
     *        dp[i][j] = dp[i-1][j]  (preceding element matches multiple times)
     *        dp[i][j] = dp[i][j-1]  (preceding element matches one time)
     *        dp[i][j] = dp[i][j-2]  (preceding element matches zero time)
     *
     */
    public boolean isMatch(String s, String p) {

    }
}
