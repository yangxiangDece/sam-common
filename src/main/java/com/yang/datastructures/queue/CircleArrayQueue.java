package com.yang.datastructures.queue;

import java.util.Scanner;

/**
 * 环形数组队列
 */
public class CircleArrayQueue {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CircleArray circleArray = new CircleArray(5);
        boolean loop = true;
        while (loop) {
            System.out.println("1：显示队列 2：添加队列 3：获取队列 4：队列头部数据 5：队列有效数据 6：队列指针情况 7：退出");
            int op = scanner.nextInt();
            switch (op) {
                case 1:
                    try {
                        circleArray.showQueue();
                    } catch (Exception e) {
                        System.out.println("队列为空...");
                    }
                    break;
                case 2:
                    System.out.println("请输入数据：");
                    int value = scanner.nextInt();
                    circleArray.add(value);
                    break;
                case 3:
                    try {
                        System.out.println("获取队列：" + circleArray.get());
                    } catch (Exception e) {
                        System.out.println("队列为空...");
                    }
                    break;
                case 4:
                    try {
                        System.out.println("队列头部数据：" + circleArray.headQueue());
                    } catch (Exception e) {
                        System.out.println("队列为空...");
                    }
                    break;
                case 5:
                    System.out.println("队列有效数据:" + circleArray.size());
                    break;
                case 6:
                    circleArray.detail();
                    break;
                case 7:
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class CircleArray {

    // 容器的容量
    private int size;
    // 队列长度
    private int length;
    // 队列头部下标 初始为0
    private int head;
    // 队列尾部下标 初始为0
    private int tail;
    // 存放数据的容器
    private int[] arrays;

    public CircleArray(int size) {
        if (size < 1) {
            throw new RuntimeException("容器容量不能为0");
        }
        this.size = size;
        arrays = new int[size];
    }
    // 0  1  2  3 4
    // 口 口 口 口 口

    // 队列是否已满
    public boolean isFull() {
        return length == size;
    }

    // 队列是否为空
    public boolean isEmpty() {
        return length == 0;
    }

    // 添加数据到队列
    public void add(int value) {
        if (isFull()) {
            System.out.println("队列已满，无法添加数据...");
            return;
        }
        arrays[tail] = value;
        // 添加数据后，tail需要后移 因为是环形队列 所以tail可能数组越界，需要采用取模的方式
        // 数学上规定：如果被除数比除数小，商为0，则余数就是被除数本身
        tail = (tail + 1) % size;
        length++;
        System.out.println("添加数据成功!");
    }

    // 从队列获取数据
    public int get() throws Exception {
        if (isEmpty()) {
            throw new RuntimeException("队列为空...");
        }
        int value = arrays[head];
        // 获取数据后，head需要后移，因为是环形队列 所以head可能数组越界，需要采用取模的方式
        head = (head + 1) % size;
        length--;
        return value;
    }

    // 显示当前队列数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空...");
            return;
        }
        for (int i = head; i < head + length; i++) {
            System.out.printf("arrays[%d]=%d\n", i % size, arrays[i % size]);
        }
    }

    // 获取当前队列的有效数据的个数
    public int size() {
        return length;
    }

    // 显示队列头部信息 只返回头部信息 不移动指针
    public int headQueue() throws Exception {
        if (isEmpty()) {
            throw new RuntimeException("队列为空...");
        }
        return arrays[head];
    }

    public void detail() {
        System.out.printf("head=%d\ttail=%d\tsize=%d\tlength=%d", head, tail, size, length);
        System.out.println();
    }
}
