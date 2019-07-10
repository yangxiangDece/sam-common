package com.yang.datastructures.sort;

import java.util.Arrays;

/**
 * 基数排序
 * 基数排序(Radix Sort)是桶排序的扩展，它的基本思想是：将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。
 * 这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arrays = CreateArrays.getArrays(8);
        System.out.print("before sort：");
        System.out.println(Arrays.toString(arrays));
        sort(arrays);
        System.out.println();
        System.out.print(" after sort：");
        System.out.println(Arrays.toString(arrays));
    }

    /**
     * 对数组按照"某个位数"进行排序(桶排序)
     *
     * @param arrays
     */
    private static void sort(int[] arrays) {

        // 找到数组中最大数的位数
        int max = getMax(arrays);
        int maxLength = String.valueOf(max).length();

        // 定义一个二维数组，表示10个桶（因为是10进制），每个桶就是一个一位数组 用来存放拆分的数
        int[][] bucket = new int[10][arrays.length];

        // 定义一个一维数组来记录每个桶中的拆分的数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 将数据放入到桶中 第一次个位，第二次十位，百位...
            for (int j = 0; j < arrays.length; j++) {
                int d = arrays[j] / n % 10;
                bucket[d][bucketElementCounts[d]] = arrays[j];
                bucketElementCounts[d]++;
            }
            int index = 0;
            // 遍历每个桶并且将桶中的数据放入到原数组中
            for (int k = 0; k < bucket.length; k++) {
                // 如果桶中有数据，就放入到原数组中
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        // 将桶中的数据 依次放入到原数组中 即覆盖原数组
                        arrays[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }
    }

    private static int getMax(int[] arrays) {
        int max = arrays[0];
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] > max) {
                max = arrays[i];
            }
        }
        return max;
    }
}
