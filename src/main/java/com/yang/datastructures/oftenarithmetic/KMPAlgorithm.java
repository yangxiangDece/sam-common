package com.yang.datastructures.oftenarithmetic;

import java.util.Arrays;

/**
 * KMP算法 字符串查找算法
 */
public class KMPAlgorithm {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index);
    }

    private static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 暴力匹配原则是一个一个的匹配，kmp是将匹配过的字符 跳过
            // 通过部分匹配表 来移动匹配指针，可以避免比较已经比较过的数据了 提高效率
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    // 获取一个字符串的部分匹配表
    private static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        // 如果字符长度为1 部分匹配值就是0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 直到发现dest.charAt(i) != dest.charAt(j) 成立才退出
            // kmp的核心算法思想
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
