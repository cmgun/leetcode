package com.cmgun.lettcode.strings;

/**
 * 9. Palindrome Number
 * https://leetcode.com/problems/palindrome-number/
 *
 * Created by cmgun on 2019/5/10
 */
public class Solution9 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(1) + "");
        System.out.println(isPalindrome(-1) + "");
        System.out.println(isPalindrome(12) + "");
        System.out.println(isPalindrome(111) + "");
        System.out.println(isPalindrome(12121) + "");
    }

    public static boolean isPalindrome(int x) {
        // negative number
        if (x < 0) {
            return false;
        }
        // reverse number of x
        int reverse = 0;
        int original = x;
        while (x > 0) {
            int rightNumber = x % 10;
            reverse = reverse * 10 + rightNumber;
            x = x / 10;
        }
        return original == reverse;
    }
}
