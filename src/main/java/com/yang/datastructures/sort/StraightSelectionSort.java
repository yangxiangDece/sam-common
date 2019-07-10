package com.yang.datastructures.sort;

import com.yang.common.CreateArrays;

import java.util.Arrays;

/**
 * 选择排序
 * 选择排序就是对数组中的元素进行比较选择，然后直接放置在排序后的位置。
 * 首先指针K先指向数组0号位置，K相当于指明一个目标位置。然后另一个指针min从K开始，往后依次比较，找到最小的值，并存储在min中，比较了一轮后，
 * min中存储的数就是整个数组中最小的数字，这时直接将min中的数字和K指向的数字交换即可。然后找到数组中第二小的数，让他跟数组中第二个元素交换一下值，以此类推。
 */
public class StraightSelectionSort {

    public static void main(String[] args) {
        int[] array = CreateArrays.getArrays(12);
        System.out.print("before sort：");
        System.out.println(Arrays.toString(array));
//        long start = System.currentTimeMillis();
        sort(array);
//        System.out.println("排序时间：" + (System.currentTimeMillis() - start));
        System.out.println();
        System.out.print("after sort：");
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array) {
        int k, temp;
        for (int i = 0; i < array.length; i++) {
            k = i;
            for (int j = i; j < array.length; j++) {
                // 这里只交换了指针，然后继续循环和现在这个新指针比较，如果有比当前这个指针指向的值更小的，
                // 那么再交换，直到这轮数组循环完，那么当前k指向的值一定是数组中最小的，后面发起交换
                if (array[j] < array[k]) {
                    k = j;
                }
            }
            if (k != i) {
                temp = array[i];
                array[i] = array[k];
                array[k] = temp;
            }
        }
    }
}
