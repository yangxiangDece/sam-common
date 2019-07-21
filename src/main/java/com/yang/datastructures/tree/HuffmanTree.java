package com.yang.datastructures.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 * 给定n个权值作为n个叶子节点，构造一颗二叉树，若该树的带权路径长度(wpl)达到最小，称这样的二叉树为最优二叉树，也称为赫夫曼树
 * 赫夫曼树是带权路径最短的树，较大的节点离根节点较近
 * <p>
 * 赫夫曼树编码
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arrays = {13, 7, 8, 3, 29, 6, 1};
        Node node = createHuffManTree(arrays);
        System.out.println("赫夫曼树的前序遍历：");
        node.preOrder();
    }

    private static Node createHuffManTree(int[] arrays) {
        List<Node> nodes = new ArrayList<>(arrays.length);
        for (int i = 0; i < arrays.length; i++) {
            nodes.add(new Node(arrays[i]));
        }
        while (nodes.size() > 1) {

            // 先排序 从小到大
            Collections.sort(nodes);
            System.out.println("nodes排序后：" + nodes);

            // 1、取出权值最小的节点（二叉树）
            Node leftNode = nodes.get(0);
            // 2、取出权值第二小的节点（二叉树）
            Node rightNode = nodes.get(1);

            // 3、构建一个新的二叉树，父节点 等于 左右节点之和
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 4、删除已经处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // 5、将新构建的二叉树加入到集合中
            nodes.add(parent);
        }
        // 返回赫夫曼树的根节点
        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
