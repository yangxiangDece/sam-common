package com.yang.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 图 邻接矩阵 邻接列表
 * <p>
 * DFS(Depth First Search) 深度优先搜索
 * BFS(Broad First Search) 广度优先搜索
 */
public class Graph {

    public static void main(String[] args) {

        // 顶点的边
        int n = 5;
        String[] vertexs = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        // 添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();

        System.out.println("深度优先算法遍历：");
        graph.dfs();
        System.out.println();
        System.out.println("广度优先算法遍历：");
        graph.bfs();
    }

    // 存储顶点集合
    private List<String> vertexList;
    // 存储图对应的邻接矩阵
    private int[][] edges;
    // 表示边的数目
    private int numOfEdges;
    // 记录某个顶点是否被访问过
    private boolean[] isVisited;
    private int n;

    public Graph(int n) {
        this.vertexList = new ArrayList<>(n);
        this.edges = new int[n][n];
        this.numOfEdges = 0;
        this.n = n;
    }

    // 得到第一个邻接顶点的下标W
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 根据前一个邻接顶点的下标来获取下一个邻接顶点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    private void dfs(boolean[] isVisited, int i) {
        // 输出
        System.out.print(getValueByIndex(i) + " -> ");
        // 将该顶点标志成已访问
        isVisited[i] = true;
        // 查找顶点i的第一个邻顶点
        int w = getFirstNeighbor(i);
        while (w != -1) {
            // 表示有邻顶点
            if (!isVisited[w]) {
                // 表示这个邻顶点没有被访问过
                dfs(isVisited, w);
            }
            // 如果w顶点已经被访问过了 就去查找当前顶点的下一个邻接顶点
            w = getNextNeighbor(i, w);
        }
    }

    // 深度优先算法
    public void dfs() {
        isVisited = new boolean[n];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    // 对一个顶点进行广度优先遍历的顶点
    private void bfs(boolean[] isVisited, int i) {
        // 表示队列的头节点对应的下标
        int u;
        // 邻接顶点w
        int w;
        // 队列记录访问过的顶点
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        // 访问顶点
        System.out.print(getValueByIndex(i) + " -> ");
        // 标记为已访问
        isVisited[i] = true;
        // 加入队列
        queue.offer(i);

        while (!queue.isEmpty()) {
            // 取出队列的头结点下标
            u = queue.poll();
            // 得到第一个邻接结点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {
                // 表示找到邻接结点 判断是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " -> ");
                    // 标记为已访问
                    isVisited[w] = true;
                    // 加入队列
                    queue.offer(w);
                }
                // 在u这一行，找w的下一个邻接点
                w = getNextNeighbor(u, w);// 这里就体现出广度优先
            }
        }
    }

    // 广度优先算法
    private void bfs(){
        isVisited = new boolean[n];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    // 插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    // 添加边
    public void insertEdge(int v1, int v2, int weight) {
        // 因为是无向图 所以这两个值一定相等
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 返回顶点的数量
    public int getNumOfEdges() {
        return vertexList.size();
    }

    // 返回边的数据
    public int getNumOfVertex() {
        return numOfEdges;
    }

    // 返回顶点i对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int Weight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
