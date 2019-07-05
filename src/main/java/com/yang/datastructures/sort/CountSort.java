package com.yang.datastructures.sort;


import java.util.Arrays;

/**
 * 计数排序
 *      1、找出待排序的数组中最大和最小的元素；
 *      2、统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
 *      3、对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
 *      4、反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
 */
public class CountSort {

    public static void main(String[] args) {
        int[] array = {12,66,222,141,38,65,97,777,88,62,111,98,54,56,17,18,23,34,15,35,25,53,51,1};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array){
        int bias, min = array[0], max = array[0];
        //找出最大值，最小值，确定数组范围
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max,array[i]);
            min = Math.min(min,array[i]);
        }
        bias = 0 - min;
        int[] temp = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            temp[array[i] + bias]++;
        }
        int index = 0, i = 0;
        while (index < array.length) {
            if (temp[i] != 0) {
                array[index] = i - bias;
                temp[i]--;
                index++;
            } else {
                i++;
            }
        }
    }
}
