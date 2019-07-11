package com.yang.datastructures.search;

/**
 * 插值查找算法
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i + 1;
        }
        int index = insertValueSearch(array, 0, array.length - 1, 11);
        System.out.println(index);
        System.out.println("查找次数：" + num);
    }

    static int num = 0;

    /**
     * @param array   原始数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 查找值
     * @return 如果找到，就返回对应的下标，如果没有找到，返回-1
     */
    public static int insertValueSearch(int[] array, int left, int right, int findVal) {
        if (left > right || findVal < array[0] || findVal > array[array.length - 1]) {
            return -1;
        }
        num++;
        // 求出mid
        int mid = left + (right - left) * (findVal - array[left]) / (array[right] - array[left]);
        int midVal = array[mid];
        if (findVal > midVal) {
            // 向右递归
            return insertValueSearch(array, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(array, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
