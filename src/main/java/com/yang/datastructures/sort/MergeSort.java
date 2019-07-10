package com.yang.datastructures.sort;


import com.yang.common.CreateArrays;

import java.util.Arrays;

/**
 * 归并排序
 * 核心思想：分而治之
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arrays = CreateArrays.getArrays(8);
        System.out.print("before sort：");
        System.out.println(Arrays.toString(arrays));
        sort(arrays);
        System.out.println();
        System.out.print(" after sort：");
        System.out.println(Arrays.toString(arrays));
    }

    private static void sort(int[] array) {
        int[] temp = new int[array.length];// 在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(array, 0, array.length - 1, temp);
    }

    private static void sort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(array, left, mid, temp);// 左边归并排序，使得左子序列有序
            sort(array, mid + 1, right, temp);// 右边归并排序，使得右子序列有序
            merge(array, left, mid, right, temp);// 将两个有序子数组合并操作
        }
    }

    private static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int t = 0;//临时数组指针
        // 1、先把左右两边(有序)的数据按照规则填充到temp数组中，直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[t++] = array[i++];
            } else {
                temp[t++] = array[j++];
            }
        }
        // 2、将剩余的元素一次填充到temp中
        // 将左边剩余元素填充进temp中
        while (i <= mid) {
            temp[t++] = array[i++];
        }
        // 将右序列剩余元素填充进temp中
        while (j <= right) {
            temp[t++] = array[j++];
        }
        // 将temp中的元素全部拷贝到原数组中
        t = 0;
        while (left <= right) {
            array[left++] = temp[t++];
        }
    }
}
