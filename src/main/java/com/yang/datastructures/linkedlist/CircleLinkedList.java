package com.yang.datastructures.linkedlist;

/**
 * 环形链表
 * 1、增删改查
 * 2、约瑟夫问题 通过循环单链表实现，移动指针来找到需要出圈的小孩，然后删除该指针的节点，指针指向该小孩的下一个节点，然后第二列继续移动，以此类推
 */
public class CircleLinkedList {

    public static void main(String[] args) {
        CircleLinked<Integer> linked = new CircleLinked<>();
//        linked.add(12);
//        linked.add(22);
//        linked.add(33);
//        linked.add(44);
//        linked.addFirst(99);
//        linked.addFirst(88);
//        System.out.println("顺序输出：");
//        linked.printLinked();
//        linked.remove(33);
//        System.out.println("删除后：");
//        linked.printLinked();

        // 约瑟夫问题 通过循环单链表实现，移动指针来找到需要出圈的小孩，然后删除该指针的节点，指针指向该小孩的下一个节点，然后第二列继续移动，以此类推
        // 创建小孩圈
        int children = 5;
        for (int i = 1; i <= children; i++) {
            linked.add(i);
        }
        CircleLinked<Integer>.Node<Integer> head = linked.getHead();
        System.out.println("初始小孩圈：");
        linked.printLinked();
        System.out.println("游戏开始：");
        josefu(1, 2, children, head);
        System.out.println("游戏结束...");

    }

    /**
     * 约瑟夫问题
     *
     * @param k 从哪个小孩开始
     * @param m 每次数几下
     * @param n 共有几个小孩 n其实就是链表的size
     */
    private static void josefu(int k, int m, int n, CircleLinked<Integer>.Node<Integer> head) {
        if (head == null) {
            System.out.println("链表为空...");
            return;
        }
        if (k <= 0 || k > n) {
            System.out.println("参数错误...");
            return;
        }
        // 先找到辅助指针，当一个节点被出圈后，需要这个节点的前一个节点来维护链表结构，来指向当前节点的下一个节点
        // 由于是按顺序来的，所以辅助指针必须是头结点的上一个节点，也就是链表的最后一个节点
        CircleLinked<Integer>.Node<Integer> helper = head;
        while (helper.getNext() != head) {
            helper = helper.getNext();
        }
        // 先让指针和辅助指针移动到指定小孩开始的位置，即 k-1
        for (int i = 0; i < k - 1; i++) {
            head = head.getNext();
            helper = helper.getNext();
        }
        // 报数几次 其实就是移动指针和辅助指针 m-1 次 然后出圈 指定圈内只有一个小孩
        do {
            for (int i = 0; i < m - 1; i++) {
                head = head.getNext();
                helper = helper.getNext();
            }
            // 这时候的 index 指针 就是要出圈的小孩了
            System.out.printf("小孩 %d 出圈\n", head.getItem());
            // 这时将 index 指针的小孩出圈
            head = head.getNext();
            helper.setNext(head);
        } while (helper != head);
        System.out.printf("小孩 %d 出圈\n", helper.getItem());
    }
}

class CircleLinked<T> {

    private Node<T> head;
    private int size;

    public Node<T> getHead() {
        return head;
    }

    public void addLast(T value) {
        if (value == null) {
            return;
        }
        final Node<T> newNode = new Node<>(value);
        Node<T> cur = head;
        if (cur == null) {
            head = newNode;
            newNode.next = head;
        } else {
            while (cur.next != head) {
                cur = cur.next;
            }
            newNode.next = cur.next;
            cur.next = newNode;
        }
        size++;
    }

    public void add(T value) {
        addLast(value);
    }

    public void addFirst(T value) {
        if (value == null) {
            return;
        }
        final Node<T> newNode = new Node<>(value);
        Node<T> h = head;
        if (h == null) {
            head = newNode;
            newNode.next = head;
        } else {
            // 2 口 口 口
            // 头结点发生了变化，所以需要将尾节点的下一个节点指向新的头节点
            Node<T> cur = head;
            while (cur.next != head) {
                cur = cur.next;
            }
            newNode.next = h;
            head = newNode;
            cur.next = head;
        }
        size++;
    }

    public void remove(T value) {
        if (size == 0) {
            System.out.println("链表为空...");
            return;
        }
        Node<T> cur = head;
        Node<T> p = head;
        do {
            if (value.equals(cur.item)) {
                // 口 口 口
                p.next = cur.next;
                if (cur == head) {
                    head = cur.next;
                }
            }
            // 保存上一个节点
            p = cur;
        } while ((cur = cur.next) != head);
    }

    public void printLinked() {
        if (size == 0) {
            System.out.println("链表为空...");
            return;
        }
        Node<T> cur = head;
        do {
            System.out.println(cur.item);
        } while ((cur = cur.next) != head);
    }

    class Node<E> {

        private E item;
        private Node<E> next;

        Node(E item) {
            this.item = item;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
