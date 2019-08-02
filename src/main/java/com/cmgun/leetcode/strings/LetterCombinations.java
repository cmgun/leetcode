package com.cmgun.leetcode.strings;


import java.util.*;

/**
 * 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Created by cmgun on 2019/8/2
 */
public class LetterCombinations {

    private static Map<Character, String[]> digitMap = new HashMap<>();

    static {
        digitMap.put('2', new String[]{"a", "b", "c"});
        digitMap.put('3', new String[]{"d", "e", "f"});
        digitMap.put('4', new String[]{"g", "h", "i"});
        digitMap.put('5', new String[]{"j", "k", "l"});
        digitMap.put('6', new String[]{"m", "n", "o"});
        digitMap.put('7', new String[]{"p", "q", "r", "s"});
        digitMap.put('8', new String[]{"t", "u", "v"});
        digitMap.put('9', new String[]{"w", "x", "y", "z"});
    }

    /**
     * Runtime: 1 ms, faster than 66.05% of Java online submissions for Letter Combinations of a Phone Number.
     * Memory Usage: 36.1 MB, less than 98.79% of Java online submissions for Letter Combinations of a Phone Number.
     */
    public static void main(String[] args) {
        // ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
        System.out.println(letterCombinations("23"));
        // ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
        System.out.println(letterCombinations("123"));
        // [a, b, c]
        System.out.println(letterCombinations("2"));
        // ["adg","adh","adi","aeg","aeh","aei","afg","afh","afi","bdg","bdh","bdi","beg","beh","bei","bfg","bfh","bfi","cdg","cdh","cdi","ceg","ceh","cei","cfg","cfh","cfi"]
        System.out.println(letterCombinations("234"));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        char[] digitArray = digits.toCharArray();
        // find the beginning
        String[] beginLetters = null;
        int start = 0;
        for (char digit : digitArray) {
            if (digitMap.containsKey(digit)) {
                beginLetters = digitMap.get(digit);
                start++;
                break;
            }
            start++;
        }
        if (beginLetters == null) {
            return result;
        }
        // reach the end
        if (start >= digitArray.length) {
            result.addAll(Arrays.asList(beginLetters));
            return result;
        }
        // start letter combination
        for (String letter : beginLetters) {
            StringBuilder currentLetter = new StringBuilder(letter);
            letterCombination(currentLetter, result, digitArray, start);
        }
        return result;
    }

    /**
     * recursion to find all combination
     */
    private static void letterCombination(StringBuilder currentLetters, List<String> result, char[] digitArray, int position) {
        // contains letter
        if (digitMap.containsKey(digitArray[position])) {
            String[] letters = digitMap.get(digitArray[position]);
            for (String letter : letters) {
                StringBuilder combination = new StringBuilder(currentLetters);
                combination.append(letter);
                if (position == digitArray.length - 1) {
                    // reach the end
                    result.add(combination.toString());
                } else {
                    // continue to recursion
                    letterCombination(combination, result, digitArray, position + 1);
                }
            }
        } else {
            // return last combination
            if (position == digitArray.length - 1) {
                // reach the end
                result.add(currentLetters.toString());
            } else {
                // continue to recursion
                letterCombination(currentLetters, result, digitArray, position + 1);
            }
        }
    }
}
