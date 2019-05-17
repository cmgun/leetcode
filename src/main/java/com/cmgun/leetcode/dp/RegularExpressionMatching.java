package com.cmgun.leetcode.dp;

/**
 * 10. Regular Expression Matching
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * Created by cmgun on 2019/5/12
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        // false
        System.out.println(isMatch("aaa", "a."));
        // true
        System.out.println(isMatch("aaa", "a*"));
        // false
        System.out.println(isMatch("aa", "a"));
        // true
        System.out.println(isMatch("ab", ".*"));
        // true
        System.out.println(isMatch("aab", "c*a*b"));
        // false
        System.out.println(isMatch("aab", "b.*"));
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
     * the result of the question is dp[s.length + 1][p.length + 1]
     *
     * borden dp[0]*  dp*[0]:
     * 1. both s and p are empty, dp[][] = true
     * 2. p is empty but s is not empty, dp[][] = false
     * 3. s is empty but p is not empty,
     *   3.1. p not like 'a*', such as 'a.', '*', 'a..', then dp[][] = false
     *   3.2. p like 'a*', such as 'a*b*', then dp[][] = true
     */
    public static boolean isMatch(String s, String p) {
        // initial dp
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // both s and p are empty string
        dp[0][0] = true;
        // s is not empty, but p is empty, apparently dp[][] is false
        for (int i = 1; i <= s.length(); i++) {
            dp[i][0] = false;
        }
        // p is not empty, but s is empty
        for (int j = 1; j <= p.length(); j++) {
            // first char in p couldn't match a empty string
            if (j == 1) {
                dp[0][j] = false;
            } else {
                // handle *
                dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
            }
        }

        // normal conditions
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) != '*') {
                    // s(0->i-1) and p(0->j-1) match result && current p.char is '.' or equals to s.char
                    dp[i][j] = dp[i -1][j - 1] && (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.');
                } else {
                    dp[i][j] = dp[i][j - 2]
                            || (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1))
                            && dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
