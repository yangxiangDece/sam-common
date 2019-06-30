package com.yang.datastructures.stack;

/**
 * 数组实现栈结构
 */
public class ArrayStack {

    public static void main(String[] args) {
        ArraysStack stack = new ArraysStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        System.out.println("入栈后：");
        stack.print();
        System.out.println("出栈后：");
        try {
            stack.pop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        stack.print();
    }
}

class ArraysStack {
    // 栈的容量大小
    private int capacity;
    // 栈中的数据个数
    private int size;
    // 存放数据的数组
    private int[] arrays;
    // 当前指针位置
    private int top = -1;

    public ArraysStack(int capacity) {
        if (capacity < 1) {
            throw new RuntimeException("栈容量大小不能小于1");
        }
        this.capacity = capacity;
        this.arrays = new int[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFll() {
        return size() == capacity;
    }

    // 入栈
    public void push(int value) {
        if (isFll()) {
            System.out.println("栈已满...");
            return;
        }
        top++;
        arrays[top] = value;
        size++;
    }

    // 出栈
    public int pop() throws Exception {
        if (isEmpty()) {
            throw new RuntimeException("栈为空...");
        }
        int temp = arrays[top];
        arrays[top] = 0;
        top--;
        size--;
        return temp;
    }

    public int size() {
        return size;
    }

    public void print() {
        for (int i = 0; i < capacity; i++) {
            System.out.println(arrays[i]);
        }
    }

    public void detail() {
        System.out.printf("capacity=%d\tsize=%d\ttop=%d\t", capacity, size(), top);
        System.out.println();
    }
}
