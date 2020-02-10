package com.cmgun.leetcode.array;

import java.util.*;

/**
 * 49. Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 *
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 *
 * Created by cmgun on 2020/2/9
 */
public class GroupAnagrams {

    /**
     * Runtime: 5 ms, faster than 99.96% of Java online submissions for Group Anagrams.
     * Memory Usage: 45.5 MB, less than 34.50% of Java online submissions for Group Anagrams.
     * @param args
     */
    public static void main(String[] args) {
        List<List<String>> result1 = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        print(result1);
    }

    /**
     * 建立一个字符串每个字母出现次数的索引，如果已有重复索引，则添加到对应索引映射的List中
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        // 处理边界
        if (strs == null) {
            return null;
        }
        if (strs.length == 1) {
            List<List<String>> result = new ArrayList<>();
            result.add(Arrays.asList(strs));
            return result;
        }

        // 索引对应的结果集位置
        int index = 0;
        // 结果集
        List<List<String>> result = new ArrayList<>();
        // 索引map
        HashMap<String, Integer> map =  new HashMap<>();

        // 循环遍历，给每个字符串的字符数组排序
        for(String str: strs) {
            String tmp = sort(str);
            // 是否在索引map中存在
            if(!map.containsKey(tmp)) {
                // 没有该索引
                List<String> newList = new ArrayList<>();
                newList.add(str);
                result.add(newList);
                map.put(tmp, index);
                index++;
            } else {
                // 已有索引，则新增到结果集对应的位置
                List<String> tmpList = result.get(map.get(tmp));
                tmpList.add(str);
                result.set(map.get(tmp), tmpList);
            }
        }

        return result;
    }

    /**
     * 获取字符串的字符集数组后排序，保证顺序与index一致
     * @param str
     * @return
     */
    private static String sort(String str) {
        char[] tmp = str.toCharArray();
        Arrays.sort(tmp);
        return new String(tmp);
    }


    public static void print(List<List<String>> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.println("----row " + i + "----");
            for (String str: result.get(i)) {
                System.out.printf(str + ",");
            }
            System.out.println("");
        }
    }
}
