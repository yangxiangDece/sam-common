package com.yang.datastructures.oftenarithmetic;

/**
 * 汉罗塔问题 分治算法
 */
public class Hanoitower {

    public static void main(String[] args) {
        hanoitower(5, 'A', 'B', 'C');
        System.out.println("移动次数：" + count);
    }

    private static int count = 0;

    private static void hanoitower(int num, char a, char b, char c) {
        count++;
        if (num == 1) {
            // 如果只有一个盘
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            // 如果有多个盘，我们总是可以看做是两个盘：最下边的一个盘和上面的所有盘
            // 先把 最上面的所有盘 A->B 移动过程会使用到c
            hanoitower(num - 1, a, c, b);
            // 把最下边的盘 A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            // 把B塔的所有盘 B->C 移动过程会使用到a塔
            hanoitower(num - 1, b, a, c);
        }
    }
}
