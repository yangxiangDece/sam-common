package com.yang.datastructures.recursion;

/**
 * 递归 回溯算法
 * 迷宫
 */
public class MiGong {

    public static void main(String[] args) {

        // 初始化迷宫
        int[][] map = new int[8][7];
        int xLength = map[0].length;
        int yLength = map.length;
        // 使用1表示墙

        // 上下设为1
        for (int i = 0; i < xLength; i++) {
            map[0][i] = 1;
            map[yLength - 1][i] = 1;
        }
        // 左右设为1
        for (int i = 0; i < yLength; i++) {
            map[i][0] = 1;
            map[i][xLength - 1] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
//        map[3][3] = 1;
        // 打印地图
        System.out.println("地图：");
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        // 走迷宫
        setWay(map, 1, 1);

        System.out.println("走迷宫后：");
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路
     * 1、i、j 从地图的哪个位置出发 (1,1)
     * 2、如果小球能到map[6][5] 位置，则说明通路已经找到，设定map[6][5] 就是迷宫的出口
     * 3、约定：
     *      当map[i][j] 为0 表示该点没有走过
     *      当map[i][j] 为1 表示墙
     *      当map[i][j] 为2 表示通路可以走
     *      当map[i][j] 为3 表示该点已经走过，但是走不通
     *
     *   走迷宫时需要指定一个策略：下->右->上->左，如果该点走不通，再回溯
     */


    /**
     * 使用递归回溯来给小球找路
     *
     * @param map 表示地图
     * @param i   表示从哪里开始的X轴
     * @param j   表示从哪里开始的Y轴
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {
                // 如果这个点还没有走过 则按照策略 下->右->上->左 走
                map[i][j] = 2;// 假定该点可以走通
                if (setWay(map, i + 1, j)) { // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { // 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) { // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) { // 向左走
                    return true;
                } else {
                    // 说明该点是走不通的，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果map[i][j] !=0，可能是1,2,3
                return false;
            }
        }
    }
}
