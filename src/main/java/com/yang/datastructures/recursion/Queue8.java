package com.yang.datastructures.recursion;

/**
 * 递归
 * 八皇后问题
 */
public class Queue8 {

    public static void main(String[] args) {

        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法", count);
    }

    static int count = 0;

    int max = 8;
    // 定义一个数组，保存皇后放置位置的结果，比如：array = {0, 4 ,5 ,2, 6, 1, 3}
    int[] array = new int[max];

    /**
     * 放置皇后
     * check 是 每一次递归时，进入到check中都有 for (int i = 0; i < max; i++) ，因此会有回溯
     *
     * @param n
     */
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后n，放入到该行的第1列
            array[n] = i;
            // 判断当前放置第n个皇后到i列时，是否冲突
            if (judge(n)) { // 不冲突
                // 接着放n+1个皇后，即开始递归
                check(n + 1);
            }
            // 如果不冲突，就继续执行array[n] = i； 即将第n个皇后放置在本行的 后一个位置
        }
    }

    /**
     * 查看当我们放置第n个皇后，就去检测皇后是否和前面已经摆好的皇后冲突
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 1. array[i] == array[n] 表示判断 第n个皇后是否和前面的n-1个皇后在同一列
            // 2. Math.abs(n - i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线上
            // 3. 不用判断是否在同一行，因为n 每次都在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        for (int i = 0; i < max; i++) {
            System.out.print(array[i] + " ");
        }
        count++;
        System.out.println();
    }
}
