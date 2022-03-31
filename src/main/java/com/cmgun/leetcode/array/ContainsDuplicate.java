package com.cmgun.leetcode.array;

import java.util.HashSet;

/**
 * 217. Contains Duplicate
 * https://leetcode-cn.com/problems/contains-duplicate/
 *
 * Created by cmgun on 2022/3/31
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        // true
        System.out.println(containsDuplicate(new int[]{1,2,3,1}));
        // false
        System.out.println(containsDuplicate(new int[]{1,2,3,4}));
        // true
        System.out.println(containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2}));
    }

    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (Integer x : nums) {
            if (set.contains(x)) {
                return true;
            } else {
                set.add(x);
            }
        }
        return false;
    }
}
