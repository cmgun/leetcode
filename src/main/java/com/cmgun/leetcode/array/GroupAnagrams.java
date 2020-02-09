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
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * 建立一个字符串每个字母出现次数的索引，如果已有重复索引，则添加到对应索引映射的List中
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // 处理边界
        if (strs == null) {
            return null;
        }
        if (strs.length == 1) {
            List<List<String>> result = new ArrayList<>();
            result.add(Arrays.asList(strs));
            return result;
        }
        // 建立索引，key=字母，value=出现次数
        List<HashMap<Character, Integer>> indexList = new ArrayList<>();
        // 结果集
        List<List<String>> result = new ArrayList<>();

        // 初始索引建立
        char[] chars = strs[0].toCharArray();
        HashMap<Character, Integer> initIndex = generateIndex(chars);
        indexList.add(initIndex);
        result.add(Arrays.asList(strs[0]));


        for (int i = 1; i < strs.length; i++) {
            char[] charArray = strs[i].toCharArray();
            // 生成索引
            HashMap<Character, Integer> index = generateIndex(charArray);
            boolean sameIndex = false;
            // 遍历索引
            for (int j = 0; j < indexList.size(); j++) {
                // 是否已有索引
                if (sameIndex(index, indexList.get(j))) {
                    // 添加到结果集
                    result.get(j).add(strs[i]);
                    sameIndex = true;
                    break;
                }
            }
            // 新索引，则新增
            if (!sameIndex) {
                indexList.add(index);
                result.add(Arrays.asList(strs[i]));
            }
        }
        return result;

    }

    /**
     * 生成字母频次索引
     * @param chars
     * @return
     */
    private static HashMap<Character, Integer> generateIndex(char[] chars) {
        HashMap<Character, Integer> index = new HashMap<>();
        for (Character c : chars) {
            if (!index.containsKey(c)) {
                // 没有该字母，新增
                index.put(c, 0);
            }
            // 对应value+1
            index.put(c, index.get(c) + 1);
        }
        return index;
    }

    /**
     * 是否相同索引
     * @param index1
     * @param index2
     * @return
     */
    private static boolean sameIndex(HashMap<Character, Integer> index1, HashMap<Character, Integer> index2) {
        if (index1.size() != index2.size()) {
            // 长度不同，不相同
            return false;
        }
        // 比较每个字母频次
        for (Map.Entry<Character, Integer> entry : index1.entrySet()) {
            if (!index2.containsKey(entry.getKey()) || !index2.get(entry.getKey()).equals(entry.getValue())) {
                // key不同，或 key相同，value不同
                return false;
            }
        }
        return true;
    }


    public static void print(List<List<String>> result) {

    }
}
