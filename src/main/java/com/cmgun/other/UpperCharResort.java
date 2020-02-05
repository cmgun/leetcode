package com.cmgun.other;

/**
 * 把一个字符串的大写字母放到字符串的后面，各个字符的相对位置不变，且不能申请额外的空间。示例：输入：AkleBiCeilD 输出：kleieilABCD
 *
 * Created by cmgun on 2020/2/5
 */
public class UpperCharResort {

    public static void main(String[] args) {
        System.out.println(resort("AkleBiCeilD"));
    }

    public static char[] resort(String rawString) {
        // 空字符串或者单个字符，直接返回
        if (rawString == null) {
            return new char[]{};
        }
        if (rawString.length() == 1) {
            return rawString.toCharArray();
        }
        char[] chars = rawString.toCharArray();
        char temp;
        // 从后往前，找大写字母
        for (int i = chars.length - 1; i >= 0; i--) {
            if ('A' <= chars[i] && chars[i] <= 'Z') {
                int j = i;
                // 后移到合适位置
                while (j + 1 <= chars.length - 1 && !('A' <= chars[j + 1] && chars[j + 1] <= 'Z')) {
                    // 交换
                    temp = chars[j + 1];
                    chars[j + 1] = chars[j];
                    chars[j] = temp;
                    j++;
                }
            }
        }
        return chars;
    }
}
