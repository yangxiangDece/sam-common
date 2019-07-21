package com.yang.datastructures.tree;

import java.util.*;

/**
 * 赫夫曼树编码
 */
public class HuffmanCode {

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        List<CodeNode> nodes = getNodes(bytes);
        System.out.println(nodes);

        CodeNode huffManTree = createHuffManTree(nodes);
        System.out.println("构建的赫夫曼树后，前序遍历：");
        huffManTree.preOrder();

        System.out.println("生成赫夫曼编码表：" + getCodes(huffManTree));
    }

    // 将赫夫曼编码表存放在map中，形式：32=01 97=100...
    private static Map<Byte, String> huffmanCodes = new HashMap<>();

    private static Map<Byte, String> getCodes(CodeNode root) {
        if (root == null) {
            return null;
        }
        // 在生产赫夫曼编码表，需要拼接路径，左边的为：0 右边的为：1
        StringBuilder builder = new StringBuilder();
        // 处理root节点的左子树
        getCodes(root.left, "0", builder);
        // 处理root节点的右子树
        getCodes(root.right, "1", builder);
        return huffmanCodes;
    }

    /**
     * 生成赫夫曼编码
     *
     * @param node          节点
     * @param code          路径，左边节点是0，右边节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(CodeNode node, String code, StringBuilder stringBuilder) {
        StringBuilder sb = new StringBuilder(stringBuilder);
        sb.append(code);
        if (node != null) {
            // 判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {
                // 是一个非叶子节点
                // 向左递归
                getCodes(node.left, "0", sb);
                // 向右递归
                getCodes(node.right, "1", sb);
            } else {
                // 是一个叶子节点
                huffmanCodes.put(node.data, sb.toString());
            }
        }
    }

    private static List<CodeNode> getNodes(byte[] bytes) {
        List<CodeNode> nodes = new ArrayList<>(bytes.length);

        // 统计每个字符出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            // 如果不存在则将这个key的值设为1
            // 如果存在 则在原来的基础上加1
            counts.merge(b, 1, (oldValue, value) -> oldValue + value);
        }
        // 根据字符出现的次数 作为权值 构建node 加入到集合中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new CodeNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private static CodeNode createHuffManTree(List<CodeNode> nodes) {

        while (nodes.size() > 1) {
            // 先排序
            Collections.sort(nodes);
            // 1、取出权值最小的节点（二叉树）
            CodeNode leftNode = nodes.get(0);
            // 2、取出权值第二小的节点（二叉树）
            CodeNode rightNode = nodes.get(1);

            // 3、构建一个新的二叉树，父节点 等于 左右节点之和
            CodeNode parent = new CodeNode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            // 4、删除已经处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // 5、将新构建的二叉树加入到集合中
            nodes.add(parent);
        }
        // 返回赫夫曼树
        return nodes.get(0);
    }
}

class CodeNode implements Comparable<CodeNode> {

    Byte data; // 存放数据(字符)本身，比如'a' -> 97
    int weight; // 权值 表示字符出现的次数
    CodeNode left;
    CodeNode right;

    public CodeNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

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
    public int compareTo(CodeNode o) {
        // 从小到大 排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "CodeNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
