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

    }

    public static int romanToInt(String s) {

    }
}
