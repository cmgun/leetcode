package com.cmgun.leetcode.strings;


/**
 * 387. First Unique Character in a String
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 *
 * Created by cmgun on 2022/4/2
 */
public class FirstUniqueCharacterinaString {

    public static void main(String[] args) {
        // 0
        System.out.println(firstUniqChar("leetcode"));
        // 2
        System.out.println(firstUniqChar("loveleetcode"));
        // -1
        System.out.println(firstUniqChar("aabb"));
    }

    public static int firstUniqChar(String s) {
        int [] count = new int[26];
        char[] list = s.toCharArray();
        for (char c : list) {
            count[c - 'a']++;
        }
        for (int i = 0; i < list.length; i++) {
            char c = list[i];
            if (count[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
