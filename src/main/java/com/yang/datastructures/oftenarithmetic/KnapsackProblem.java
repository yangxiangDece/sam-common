package com.yang.datastructures.oftenarithmetic;

/**
 * 背包问题 动态规划算法
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        // 物品的重量
        int[] w = {1, 4, 3, 5, 6};
        // 物品的价值
        int[] val = {1500, 2000, 3000, 7000, 6000};
        // 背包的重量
        int m = 6;
        // 物品的个数
        int n = val.length;

        // v[i][j] 表示前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        // 记录商品存放的情况
        int[][] path = new int[n + 1][m + 1];

        // 初始化第一行和第一列
        for (int i = 0; i < v.length; i++) {
            // 将第一列设为0
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            // 将第一行设为0
            v[0][i] = 0;
        }

        // 动态规划算法 通过公式处理
        for (int i = 1; i < v.length; i++) { // 不处理第一行 i从1开始
            for (int j = 1; j < v[0].length; j++) { // 不处理第一列，j从1开始
                if (w[i - 1] > j) {
                    // 因为i从1开始 所以w[i] 需要变成w[i - 1]
                    v[i][j] = v[i - 1][j];
                } else {
                    // v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]])
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        // 记录当前数据到path中
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        int i = path.length - 1;// 行的最大下标
        int j = path[0].length - 1;// 列的最大下标
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
