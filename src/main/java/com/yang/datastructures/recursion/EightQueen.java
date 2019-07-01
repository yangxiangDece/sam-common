package com.yang.datastructures.recursion;

/**
 * 八皇后问题
 */
public class EightQueen {

    private static int[] queen = {-1, -1, -1, -1, -1, -1, -1, -1};
    private static int count = 0;

    // 判断某个皇后是否与已有皇后冲突
    /**
     *
     *  0 0 0 0 0 0 0 0
     *  0 0 0 0 0 0 0 0
     *  0 0 0 0 0 0 0 0
     *  0 0 0 0 0 0 0 0
     *  0 0 0 0 0 0 0 0
     *  0 0 0 0 0 0 0 0
     *  0 0 0 0 0 0 0 0
     *  0 0 0 0 0 0 0 0
     *
     */
    private static boolean available(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (col == queen[i]) {
                // 同一列拒绝
                return false;
            }
            if ((row - i) == (col - queen[i])) {
                // 同一主对角线
                return false;
            }
            if ((row - i) + (col - queen[i]) == 0) {
                // 同一副对角线
                return false;
            }
        }
        return true;
    }

    private static void findSpace(int queenNumber) {
        //从0 ~ 7遍历这一行的8个空位
        for (int i = 0; i < 8; i++) {
            if (available(queenNumber, i)) {
                //如果可以放这个位置就记录下第queenNumber个皇后的位置
                queen[queenNumber] = i;
                if (queenNumber == 7) {//如果八个皇后都放满了统计一下
                    count++;
                    print();
                    return;
                }
                int nextNumber = queenNumber + 1;//还有皇后没放，递归放下一个皇后
                findSpace(nextNumber);
            }
        }
        queen[queenNumber] = -1; // 如果这一行没有可放的位置说明上一行皇后放的位置不行，要为上一个皇后寻找新的可放位置
        queenNumber--;
    }

    private static void print() {
        for (int i = 0; i < queen.length; i++) {
            System.out.print(queen[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        findSpace(0);
        System.out.printf("一共有%d种解法", count);
    }
}
