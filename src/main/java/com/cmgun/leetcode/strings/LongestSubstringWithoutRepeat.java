package com.cmgun.leetcode.strings;


import java.util.HashMap;

/**
 * 3. Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Created by cmgun on 2020/2/11
 */
public class LongestSubstringWithoutRepeat {

    /**
     * Result:
     * Runtime: 7 ms
     * Memory Usage: 41.9 MB
     */
    public static void main(String[] args) {
        // 3
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        // 1
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        // 3
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    /**
     * 使用HashMap存储当前最长的非重复元素，记录最长subString长度。
     * 遍历中维护两个指针：目标字符串遍历游标、HashMap中最小下标
     * 1.如果有重复，更新重复元素的下标index
     *   当前HashMap最小下标为出现重复元素的下标位置
     *   比较 (当前位置 - Map最小下标) 是否大于已有的最长长度，如果是则更新最长长度；
     * 2.如果不重复，直接新增到结果集中
     */
    public static int lengthOfLongestSubstring(String s) {
        // 处理边界，空或单元素
        if (s == null) {
            return 0;
        }
        if (s.length() <= 1) {
            return s.length();
        }

        // 记录非重复元素
        // char对应的下标
        HashMap<Character, Integer> charMaps = new HashMap<>();
        // 最大长度
        int max = 0;
        for (int mapIndex = 0, stringIndex = 0; stringIndex < s.length(); stringIndex++) {
            // 重复元素
            if (charMaps.containsKey(s.charAt(stringIndex))) {
                // 更新map的Index
                mapIndex = Math.max(charMaps.get(s.charAt(stringIndex)), mapIndex);
            }
            // 当前最大长度 = max{当前最长长度, stringIndex(遍历游标) - mapIndex(最大重复元素下标) + 1}
            max = Math.max(stringIndex - mapIndex + 1, max);
            // 记录当前最小下标位置（下标+1，从1开始）
            charMaps.put(s.charAt(stringIndex), stringIndex + 1);
        }
        return max;
    }
}
