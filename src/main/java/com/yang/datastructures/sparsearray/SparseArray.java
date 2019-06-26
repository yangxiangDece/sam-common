package com.yang.datastructures.sparsearray;

/**
 * @author YangXiang
 * @description 稀疏数组
 * @time 2019/6/26 20:33
 */
public class SparseArray {

    public static void main(String[] args) {

        // 8 * 8 的棋盘
        int[][] arrays = new int[8][8];
        // 0-无子 1-黑子 2-白子
        // 初始化棋盘
        arrays[1][2] = 1;
        arrays[2][3] = 2;
        arrays[3][3] = 1;
        arrays[4][4] = 2;

        System.out.println("初始棋盘：");
        printArray(arrays);

        // 获取不为0的棋子个数 由于下标从0开始 所以稀疏数组的行的长度需要加一
        int sum = getValid(arrays) + 1;
        int[][] sparseArray = new int[sum][3];
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
        System.out.println("填充稀疏数组值后");
        System.out.println("行\t列\t值");
        printArray(sparseArray);
    }

    private static int getValid(int[][] arrays) {
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
