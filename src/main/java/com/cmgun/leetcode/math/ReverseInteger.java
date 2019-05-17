package com.cmgun.leetcode.math;

/**
 * 7. Reverse Integer
 * https://leetcode.com/problems/reverse-integer/
 *
 * Created by cmgun on 2019/3/1
 */
public class ReverseInteger {

    public static void main(String[] args) {
        // print 321
        System.out.println(reverse(123));
        // print 21
        System.out.println(reverse(120));
        // print -123
        System.out.println(reverse(-321));
        // print -21
        System.out.println(reverse(-120));
        // print 0
        System.out.println(reverse(Integer.MAX_VALUE + 1));
    }

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || rev == Integer.MAX_VALUE / 10 && pop > 7) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || rev == Integer.MIN_VALUE / 10 && pop < -8) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
