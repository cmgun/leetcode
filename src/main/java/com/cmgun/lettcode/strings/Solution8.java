package com.cmgun.lettcode.strings;

/**
 * 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 *
 * Created by cmgun on 2019/5/10
 */
public class Solution8 {


    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("   -  42"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
        System.out.println(myAtoi("1"));
        System.out.println(myAtoi("+1"));
        System.out.println(myAtoi("   +0 1"));
        System.out.println(myAtoi("   -42"));
    }

    public static int myAtoi(String str) {
        // empty or null
        if (str == null || str.length() == 0) {
            return 0;
        }
        // transfer to chars, remove the white space
        char[] chars = str.trim().toCharArray();
        if (chars.length == 0) {
            return 0;
        }
        // handle the signs
        int start = 0;
        int sign = 1;
        if (chars[start] == '+' || chars[start] == '-') {
            sign = chars[start] == '+' ? 1 : -1;
            if (chars.length < 2 || !Character.isDigit(chars[1])) {
                return 0;
            }
            start++;
        }
        long result = 0;
        for (; start < chars.length; start++) {
            char ch = chars[start];
            // is not number, break
            if (ch < 48 || ch > 57) {
                break;
            }
            int num = ch - '0';
            result = result * 10 + num;
            // out of range
            if (sign > 0 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign < 0 && -1 * result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return sign * (int) result;
    }
}
