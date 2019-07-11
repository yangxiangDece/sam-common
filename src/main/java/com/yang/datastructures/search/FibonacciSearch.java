package com.yang.datastructures.search;

import java.util.Arrays;

/**
 * 斐波那契查找算法
 */
public class FibonacciSearch {

    public static void main(String[] args) {
        int[] arrays = {1, 8, 10, 89, 1000, 1234};
        int index = fibSearch(arrays, 1000);
        System.out.println(index);
    }

    private static int maxSize = 20;

    // mid = low + F(k - 1) - 1
    // 得到一个斐波那契数列
    private static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    private static int fibSearch(int[] array, int key) {
        int low = 0;
        int height = array.length - 1;
        int k = 0; // 表示斐波那契分割数值的下标
        int mid = 0;
        int f[] = fib(); // 获取到斐波那契数列
        // 获取到斐波那契分割数值的下标
        while (height > f[k] - 1) {
            k++;
        }
        // 因为 f[k] 的值 可能大于 array 的长度，因此我们需要使用Arrays类，构造一个新数组，并指向array
        // 不足的部分使用0填充
        int[] temp = Arrays.copyOf(array, f[k]);
        // 实际上需求使用array数组最后的数填充temp
        for (int i = height + 1; i < temp.length; i++) {
            temp[i] = array[height];
        }
        while (low <= height) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                // 继续向左边查找
                height = mid - 1;
                // 全部元素 = 前面的元素 + 后面的元素
                // f[k] = f[k - 1] + f[k - 2]
                // 因为 前面有f[k-1]个元素，所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                // 即 在f[k-1] 的前面继续查找 k-- 下次循环mid=f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {
                // 继续向右边查找
                low = mid + 1;
                // 全部元素 = 前面的元素 + 后面的元素
                // f[k] = f[k - 1] + f[k - 2]
                // 因为 前面有f[k-2]个元素，所以可以继续拆分f[k-1] = f[k-3] + f[k-4]
                // 即 在f[k-2] 的前面继续查找 k -= 2 下次循环mid=f[k-1-2]-1
                k -= 2;
            } else {
                // 找到
                if (mid <= height) {
                    return mid;
                } else {
                    return height;
                }
            }
        }
        return -1;
    }
}
