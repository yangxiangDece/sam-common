package com.yang.datastructures.sparsearray;

/**
 * 稀疏数组
 * <p>
 * 稀疏数组特点：
 * 第一行为统计数据：第一行第一列为总行数 第一行第二列为总列数 第一行第三列为有效值的个数
 * 后面的就是通过 row 和 col 定位坐标位置 val表示那个坐标点上的值
 * row   col   val
 * 8     8     5
 * 1     2     2
 * 3     1     1
 * 4     5     2
 * 5     6     1
 */
public class SparseArray {

    public static void main(String[] args) {
        // 8 * 8 的棋盘(数组)
        int[][] arrays = new int[8][8];
        // 0-无子 1-黑子 2-白子
        arrays[1][2] = 1;
        arrays[2][3] = 2;
        arrays[3][3] = 1;
        arrays[4][4] = 2;

        System.out.println("初始二维数组：");
        printArray(arrays);

        /**
         * 二维数组转稀疏数组
         */
        // 获取有效值的个数
        int sum = getValidValueSum(arrays);
        // 因为下标从0开始 所以sum + 1
        int[][] sparseArray = new int[sum + 1][3];
        // 稀疏数组的第一行是统计有多少行、多少列、共有多少个有效值
        sparseArray[0][0] = 8;
        sparseArray[0][1] = 8;
        sparseArray[0][2] = sum;
        System.out.println("初始稀疏数组：");
        System.out.println("行\t列\t值");
        printArray(sparseArray);

        // 将二维数组的值填充到稀疏数组中
        int count = 0; // 计数当前是第几个不为0的棋子了 也就是稀疏数组的下标
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[0].length; j++) {
                if (arrays[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i; // 行
                    sparseArray[count][1] = j; // 列
                    sparseArray[count][2] = arrays[i][j]; // 值
                }
            }
        }
        System.out.println("二位数组 => 稀疏数组：");
        System.out.println("行\t列\t值");
        printArray(sparseArray);

        /**
         * 稀疏数组转二维数组
         */
        // 稀疏数组的第一行第一列为 行 ，第一行第二列为 列
        int[][] newArrays = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            newArrays[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        System.out.println("稀疏数组 => 二位数组：");
        printArray(newArrays);
    }

    private static int getValidValueSum(int[][] arrays) {
        int sum = 0;
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[0].length; j++) {
                if (arrays[i][j] != 0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private static void printArray(int[][] arrays) {
        for (int[] array : arrays) {
            for (int value : array) {
                System.out.printf("%d\t", value);
            }
            System.out.println();
        }
    }
}
