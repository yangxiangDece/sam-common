package com.yang.datastructures.oftenarithmetic;

/**
 * 二分查找算法
 */
public class BinarySearch {

    private static int num = 0;

    public static void main(String[] args) {
        int[] arrays = {2, 4, 23, 45, 56, 67, 89, 111, 123, 134, 156, 178, 212, 222, 234, 245, 267, 289};
//        System.out.println("数组下标为：" + binSearch(arrays, 0, arrays.length - 1, 123));
//        System.out.println("查找次数：" + num + "次");
        System.out.println("数组下标为：" + binarySearch(arrays, 134));
        System.out.println("查找次数：" + num + "次");
    }

    // 二分查找非递归
    private static int binarySearch(int[] arrays, int target) {
        int mid = (arrays.length - 1) / 2;
        if (arrays[mid] == target) {
            return mid;
        }
        int left = 0;
        int right = arrays.length - 1;
        while (left <= right) {
            num++;
            // 继续查找
            mid = (left + right) / 2;
            if (arrays[mid] == target) {
                return mid;
            } else if (arrays[mid] > target) {
                // 向左边查找
                right = mid - 1;
            } else {
                // 向右边查找
                left = mid + 1;
            }
        }
        return -1;
    }
}
