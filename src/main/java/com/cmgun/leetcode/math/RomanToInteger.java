package com.cmgun.leetcode.math;

import java.util.HashMap;

/**
 * 13. Roman to Integer
 * https://leetcode.com/problems/roman-to-integer/
 *
 * Created by cmgun on 2019/5/21
 */
public class RomanToInteger {

    private static HashMap<Character, Integer> charMap = new HashMap<>();

    static {
        charMap.put('I', 1);
        charMap.put('V', 5);
        charMap.put('X', 10);
        charMap.put('L', 50);
        charMap.put('C', 100);
        charMap.put('D', 500);
        charMap.put('M', 1000);
    }

    public static void main(String[] args) {
        // 3
        System.out.println(romanToInt("III"));
        // 4
        System.out.println(romanToInt("IV"));
        // 9
        System.out.println(romanToInt("IX"));
        // 58
        System.out.println(romanToInt("LVIII"));
        // 1994
        System.out.println(romanToInt("MCMXCIV"));
    }

    /**
     * rules: compare current char with last char
     * 1. if current char <= last char, result += current char;
     * 2. if current char > last char, result = result - last char + (current char - last char)
     *
     */
    public static int romanToInt(String s) {
        // the range of s is 1 ~ 3999, so s must not be empty.
        char[] chars = s.toCharArray();
        int result = charMap.get(chars[0]);
        int lastNum = result;
        for (int i = 1; i < chars.length; i++) {
            int current = charMap.get(chars[i]);
            if (current > lastNum) {
                result = result - 2 * lastNum + current;
            } else {
                result += current;
            }
            lastNum = current;
        }
        return result;
    }
}
