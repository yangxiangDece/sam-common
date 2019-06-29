package com.yang.datastructures.linkedlist;

/**
 * 双链表
 * 1、顺序遍历、逆序遍历
 * 2、增删改查
 */
public class DoubleLinkedList {

    public static void main(String[] args) {
        DoubleLinked<Integer> linked = new DoubleLinked<>();
        linked.add(12);
        linked.add(44);
        linked.add(98);
        linked.add(78);
        linked.addFirst(34);
        linked.add(1, 66);
        System.out.println("顺序遍历：");
        linked.printLinked();
        linked.remove(44);
        System.out.println("删除后");
        linked.printLinked();
        System.out.println("逆序遍历：");
        linked.printReversedLinked();
    }
}

class DoubleLinked<T> {

    private Node<T> head;

    private int size;

    public T getReversedByIndex(int index) {
        if (index > size || index < 0) {
            System.out.println("索引位置不正确...");
            return null;
        }
        Node<T> n = head;
        for (int i = 0; i < size - index; i++) {
            n = n.next;
        }
        return n.item;
    }

    public void add(T value) {
        addLast(value);
    }

    public void addLast(T value) {
        if (value == null) {
            return;
        }
        final Node<T> newNode = new Node<>(value);
        Node<T> n = head;
        if (n == null) {
            head = newNode;
        } else {
            while (n.next != null) {
                n = n.next;
            }
            n.next = newNode;
            newNode.prev = n;
        }
        size++;
    }

    public void addFirst(T value) {
        final Node<T> newNode = new Node<>(value);
        Node<T> h = head;
        if (h == null) {
            head = newNode;
        } else {
            newNode.next = h;
            h.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void add(int index, T value) {
        if (index < 0) {
            System.out.println("索引位置不正确...");
            return;
        }
        if (index > size) {
            System.out.println("索引位置大于链表长度...");
            return;
        }
        if (index == 0) {
            // insert head
            addFirst(value);
        } else {
            Node<T> n = head;
            do {
                n = n.next;
                index--;
            } while (index > 0);
            final Node<T> newNode = new Node<>(value);
            // 口 口 2 n 口 口
            newNode.next = n;
            newNode.prev = n.prev;
            n.prev.next = newNode;
            n.prev = newNode;
            size++;
        }
    }

    public void remove(T value) {
        if (value == null) {
            return;
        }
        Node<T> n = head;
        do {
            if (value.equals(n.item)) {
                if (n.prev == null && n.next == null) {
                    // 链表只有头结点
                    head = null;
                } else if (n.prev == null) {
                    // 口 口
                    // 是头结点 但是有下一个节点 即至少有两个节点
                    n.next.prev = null;
                    head = n.next;
                } else if (n.next == null) {
                    // 口 口
                    // 是尾结点 但是有上一个节点 即至少有两个节点
                    n.prev.next = null;
                } else {
                    // 口 口 口
                    // 中间节点
                    n.prev.next = n.next;
                    n.next.prev = n.prev;
                }
                size--;
                return;
            }
        } while ((n = n.next) != null);
    }

    public void printLinked() {
        if (size == 0) {
            System.out.println("链表为空...");
        }
        for (Node<T> n = head; n != null; n = n.next) {
            System.out.println(n.item);
        }
    }

    public void printReversedLinked() {
        if (size == 0) {
            System.out.println("链表为空...");
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        for (Node<T> n = tail; n != null; n = n.prev) {
            System.out.println(n.item);
        }
    }

    public int size() {
        return size;
    }

    class Node<E> {

        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(E item) {
            this.item = item;
        }
    }
}
