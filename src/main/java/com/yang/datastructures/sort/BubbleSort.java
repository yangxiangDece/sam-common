package com.yang.datastructures.sort;

import java.util.Arrays;

/**
 *
 冒泡排序（Bubble Sort）
 插入排序（Insertion Sort）
 希尔排序（Shell Sort）
 选择排序（Selection Sort）
 快速排序（Quick Sort）
 归并排序（Merge Sort）
 堆排序（Heap Sort）

 计数排序（Counting Sort）
 桶排序（Bucket Sort）
 基数排序（Radix Sort）
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {10,234,39,4,26,35,13,53,34,35,54,23,21554,3,34563,24,21,65,112,54,42,46,29};
        System.out.print("before sort：");
        System.out.println(Arrays.toString(array));
        sort(array);
        System.out.println();
        System.out.print("after sort：");
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array){
        for (int i = 0;i<array.length;i++) {
            for (int j = i;j<array.length;j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
