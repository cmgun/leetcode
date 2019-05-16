package com.cmgun.lettcode.dp;

/**
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * Created by cmgun on 2019/4/9
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        // bbb
        System.out.println(longestPalindrome("abbbc"));
        // a
        System.out.println(longestPalindrome("abcd"));
        // abbba
        System.out.println(longestPalindrome("abbba"));
        // aba
        System.out.println(longestPalindrome("babad"));
    }


    /**
     * idea of dynamic programing
     * state array : palindrome[i][j], marks the substring[i,j] is palindromic or not
     * initial value: i=j, palindrome[i][j]=true
     * 1.if i=j+1 or i=j, same or adjacent position, palindrome[i][j] = s[i]==s[j]
     * 2.if i>j+1, not adjacent position, palindrome[i][j] = (s[i]==s[j]) && palindrome[i-1][j+1]
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] oriChars = s.toCharArray();
        // longest palindromic substring
        int start = 0;
        int end = 0;
        // initial state array
        boolean[][] palindrome = new boolean[oriChars.length][oriChars.length];

        for (int i = 0; i < oriChars.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j || i == j+1) {
                    // same or adjacent position
                    palindrome[i][j] = oriChars[i] == oriChars[j];
                } else {
                    // other
                    palindrome[i][j] = oriChars[i] == oriChars[j] && palindrome[i-1][j+1];
                }
                // longest substring
                if (palindrome[i][j] && i - j >= end - start) {
                    start = j;
                    end = i;
                }
            }
        }
        return s.substring(start, end+1);
    }

}
