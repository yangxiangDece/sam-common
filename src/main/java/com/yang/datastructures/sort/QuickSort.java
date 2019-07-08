package com.yang.datastructures.sort;

import java.util.Arrays;

/**
 * 快速排序
 * <p>
 * 快速排序是对冒泡排序的一种改进。基本思想：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的数据要小，然后在按此方法
 * 对这两部分数据分别进行快速排序，整个排序的过程可以递归进行，直到达到整个数据变得有序。
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arrays = CreateArrays.getArrays(8);
        System.out.print("before sort：");
        System.out.println(Arrays.toString(arrays));
        quickSort(arrays);
        System.out.println();
        System.out.print(" after sort：");
        System.out.println(Arrays.toString(arrays));
    }

    private static int getMiddle(int[] arrays, int left, int right) {
        int temp = arrays[left];//将数组中的第一个作为中轴
        while (left < right) {
            //从右边向左边走
            while (left < right && arrays[right] > temp) {
                right--;
            }
            arrays[left] = arrays[right];//将右边小于中轴的记录移到左边去
            //从左边向右边走
            while (left < right && arrays[left] < temp) {
                left++;
            }
            arrays[right] = arrays[left];//将左边大于中轴的记录移到右边去
        }
        arrays[left] = temp;//两边向中轴走碰见
        return left;//返回中轴位置
    }

    private static void quickSort(int[] arrays, int left, int right) {
        if (left < right) {
            int middle = getMiddle(arrays, left, right);//得到中轴位置
            quickSort(arrays, left, middle - 1);//从左边到中轴位置再排序
            quickSort(arrays, middle + 1, right);//从中轴位置到右边再排序
        }
    }

    private static void quickSort(int[] arrays) {
        quickSort(arrays, 0, arrays.length - 1);
    }

}
