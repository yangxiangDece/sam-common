package com.yang.datastructures.sort;

import java.util.Arrays;

/**
 * 堆排序
 * <p>
 * 堆是具有以下性质的完全二叉树：
 * 每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；
 * 或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
 * <p>
 * 堆排序的基本思想是：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，
 * 此时末尾就为最大值。然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
 * <p>
 * 一般升序采用大顶堆，降序采用小顶堆。
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arrays = {12, 66, 222, 141, 38, 65, 97, 777, 888, 62, 111, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        System.out.print("before sort：");
        System.out.println(Arrays.toString(arrays));
        sort(arrays);
        System.out.println();
        System.out.print(" after sort：");
        System.out.println(Arrays.toString(arrays));
    }

    private static void sort(int[] array) {
        // 1.构建大顶堆
        //从第一个非叶子结点从下至上，从右至左调整结构
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        // 2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = array.length - 1; j > 0; j--) {
            swap(array, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(array, 0, j);//重新对堆进行调整
        }
    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     */
    private static void adjustHeap(int[] array, int i, int length) {
        int temp = array[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && array[k] < array[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (array[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     */
    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
