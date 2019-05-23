package com.cmgun.leetcode.math;

/**
 * 12. Integer to Roman
 * https://leetcode.com/problems/integer-to-roman/
 *
 * Created by cmgun on 2019/5/23
 */
public class IntegerToRoman  {

    /**
     * the dic of roman
     */
    private static int[] baseNums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static String[] chars = {"M", "CM" ,"D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static void main(String[] args) {
        // III
        System.out.println(intToRoman(3));
        // IV
        System.out.println(intToRoman(4));
        // IX
        System.out.println(intToRoman(9));
        // LVIII
        System.out.println(intToRoman(58));
        // MCMXCIV
        System.out.println(intToRoman(1994));
    }

    /**
     * rules:
     * if current num >= baseNums[i], than the char stays still.
     * if current num < baseNums[i], than i should be +1(move to next smaller base num)
     */
    public static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        while(num != 0) {
            for(int i = 0; i < baseNums.length; i++) {
                if(num >= baseNums[i]) {
                    result.append(chars[i]);
                    num -= baseNums[i];
                    break;
                }
            }
        }
        return result.toString();
    }
}
