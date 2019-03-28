package com.yang.arithmetic.sort;

import java.util.Arrays;

/**
 * 排序算法
 *      非线性时间比较类排序：
 *          交换排序：冒泡排序（稳定）、快速排序（不稳定）
 *          插入排序：直接插入排序（稳定）、折半插入排序、希尔排序（不稳定）
 *          选择排序：简单选择排序、堆排序 都不稳定
 *              堆排序
 *                  堆是具有以下性质的完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
 *                  堆排序的基本思想是：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，
 *                      此时末尾就为最大值。然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
 *                  一般升序采用大顶堆，降序采用小顶堆
 *          归并排序（稳定）：二路归并排序、多路归并排序
 *              二路归并排序：归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 *                  将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
 *              多路归并排序
 *      线性时间比较累排序：
 *          计数排序（稳定）
 *          桶排序（稳定）
 *          基数排序（稳定）
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
