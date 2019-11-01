package com.yang.datastructures;

/**
 * 稀疏数组
 * 数组模拟队列
 *
 * 二分查找算法
 * 分治算法
 * 动态规划算法
 * KMP算法（字符串查找算法）
 * 贪心算法  贪心算法所得到的结果不一定是最优的，但是都是相对近似最优的结果
 * 普利姆算法 最小生成树
 * 克鲁斯算法 最小生成树
 *
 *
 * B树：B-tree数即B树，B即Balanced，平衡的意思。有人把B-tree翻译成B-树，容易让人产生误解。会以为B-树是一种树，而B树又是另一种树，实际上B-tree就是指B树
 *      B树在叶子节点和非叶子节点都存放数据
 * B+树：B+树是B树的变种，特点：所有的数据都在非叶子节点(稠密索引)，并且链表中的数据都是有序的，非叶子节点相当于叶子节点的索引(稀疏索引)，叶子节点相当于是
 *      存储数据的数据层，更适合文件索引系统
 * B*树：B*树是B+树的变种，在B+树的非根和非叶子节点再增加指向兄弟的指针
 *
 * 图：图的结构很简单，就是由顶点$V$集和边$E$集构成，因此图可以表示成$G=(V, E)$。
 *      无向图对应的矩阵是一个对称矩阵，V0和V1有关联，那么V1和V0也必定有关联，因此A[0][1]和A[1][0]的值一定相等。
 *      有向图不再是一个对称矩阵。从V0可以到达V1，从V1却未必能到达V0，因此A[0][1]和A[1][0]的值不一定相等。
 *      如果在一个无向图中， 每两个顶点之间都存在条边，那么这种图结构称为无向完全图。典型的无向完全图
 *      如果在一个有向图中，每两个顶点之间都存在方向相反的两条边，那么这种图结构称为有向完全图。
 *  邻接列表：在邻接列表实现中，每一个顶点会存储一个从它这里开始的边的列表。比如，如果顶点A 有一条边到B、C和D，那么A的列表中会有3条边。
 *      邻接列表只描述了指向外部的边。A 有一条边到B，但是B没有边到A，所以 A没有出现在B的邻接列表中。查找两个顶点之间的边或者权重会比较费时，
 *      因为遍历邻接列表直到找到为止。
 *  邻接矩阵：在邻接矩阵实现中，由行和列都表示顶点，由两个顶点所决定的矩阵对应元素表示这里两个顶点是否相连、如果相连这个值表示的是相连边的权重。
 *      例如，如果从顶点A到顶点B有一条权重为 5.6 的边，那么矩阵中第A行第B列的位置的元素值应该是5.6，往这个图中添加顶点的成本非常昂贵，因为新的
 *      矩阵结果必须重新按照新的行/列创建，然后将已有的数据复制到新的矩阵中。
 *      注意：矩阵从左上到右下的一条对角线，其上的元素值必然是0。这样很容易想明白：任何一个顶点与它自身是没有连接的。
 *   在 稀疏图的情况下，每一个顶点都只会和少数几个顶点相连，这种情况下相邻列表是最佳选择。如果这个图比较密集，每一个顶点都和大多数其他顶点相连，那么相邻矩阵更合适。
 *   有向图 无向图
 *
 *  DFS(Depth First Search) 深度优先搜索
 *  BFS(Broad First Search) 广度优先搜索
 *
 */
public class Notes {
}
