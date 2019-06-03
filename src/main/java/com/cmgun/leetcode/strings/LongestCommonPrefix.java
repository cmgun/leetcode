package com.cmgun.leetcode.strings;


/**
 * 14. Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 *
 * Created by cmgun on 2019/6/3
 */
public class LongestCommonPrefix {

    /**
     * Result:
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Longest Common Prefix.
     * Memory Usage: 36.8 MB, less than 99.57% of Java online submissions for Longest Common Prefix.
     */
    public static void main(String[] args) {
        // "fl"
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
        // ""
        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));
        // ""
        System.out.println(longestCommonPrefix(new String[]{"dog","radogar","cdog"}));
    }

    /**
     * scene1: empty strs, return ""
     * scene2: strs.length = 1, return strs[0]
     * scene3: normal scene
     * 1. find the shortest string
     * 2. loop the strs, compare each strs[?] to shortest one.
     * break point: longest-common-prefix's length becomes 0 or loop end
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        // find the shortest string
        String sub = strs[0];
        int shortestLength = sub.length();
        for (int i = 1; i < strs.length; i++) {
            String current = strs[i];
            if (current.length() < shortestLength) {
                shortestLength = current.length();
                sub = strs[i];
            }
        }
        // loop strs
        for (String str : strs) {
            while (str.indexOf(sub) != 0) {
                sub = sub.substring(0, sub.length() - 1);
                if (sub.length() == 0) {
                    return "";
                }
            }
        }
        return sub;
    }
}
