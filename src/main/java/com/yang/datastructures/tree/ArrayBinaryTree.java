package com.yang.datastructures.tree;

/**
 * 顺序存储二叉树 - 使用数组
 * 特点：
 * 1、顺序二叉树通常只考虑完全二叉树
 * 2、第n个元素的左子节点 2 * n + 1
 * 3、第n个元素的右子节点 2 * n + 2
 * 4、第n个元素的父节点为 (n - 1) / 2
 */
public class ArrayBinaryTree {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrBinaryTree binaryTree = new ArrBinaryTree(array);

        System.out.println("顺序存储二叉树的前序遍历：");
        binaryTree.preOrder();
    }
}

class ArrBinaryTree {
    private int[] array;

    public ArrBinaryTree(int[] array) {
        this.array = array;
    }

    public void preOrder() {
        preOrder(0);
    }

    // 顺序存储二叉树的前序遍历
    private void preOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空，无法遍历");
        }
        // 输出当前元素
        System.out.println(array[index]);
        // 向左递归遍历
        if ((index * 2 + 1) < array.length) {
            preOrder(2 * index + 1);
        }
        // 向右递归
        if ((index * 2 + 2) < array.length) {
            preOrder(2 * index + 2);
        }
    }
}
