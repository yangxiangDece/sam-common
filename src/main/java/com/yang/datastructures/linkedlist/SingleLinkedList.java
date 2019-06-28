package com.yang.datastructures.linkedlist;

/**
 * 单链表
 */
public class SingleLinkedList {

    public static void main(String[] args) {
        SingleLinked<Integer> linked = new SingleLinked<>();
        linked.addLast(1);
        linked.addLast(34);
        linked.addLast(11);
        linked.addLast(65);
        linked.addLast(79);
        linked.addLast(121);
        linked.add(2, 888);
        linked.printLinked();
        System.out.println("删除后：");
        linked.remove(1);
        linked.printLinked();

        // 获取逆序为K的节点的值
        System.out.println("获取逆序为K的节点的值：" + linked.getReversedByIndex(2));
        // 链表反转
        System.out.println("链表反转：");
        linked.reversed2();
        linked.printLinked();
    }
}

class SingleLinked<T> {

    private Node<T> head;

    private int size;

    public void reversed() {
        if (head == null || head.next == null) {
            System.out.println("少于两个节点反转没有意义...");
            return;
        }
        // 核心思想：两两节点反转，下一个节点的next执行上一个节点
        // p：存储第一个节点
        // q：存储第一个节点的next节点，也就是第二个节点
        // r：存储第二个节点的next节点，也就是第三个节点，依次类推
        // 然后循环遍历，q.next = p 就完成了节点反转，然后p、q、r向后移
        Node<T> p = head, q = head.next, r;
        while (q != null) {
            r = q.next; // 存放当前节点的下一个节点，后续移动指针使用
            q.next = p; // 反转
            p = q; // p 向后移
            q = r; // q 向后移
        }
        // 这里设为空的原因是：这时候的头结点的next还执行了第二个节点，现在的这个头结点经过遍历后已经变成了尾节点，
        // 如果尾节点不为空又指向第二个节点，而第二个节点经过反转后又指向了尾节点，就会出现最后两个节点相互引用永不为空，链表就出现了死循环
        head.next = null;
        head = p;
    }

    public void reversed2() {
        if (head == null || head.next == null) {
            System.out.println("少于两个节点反转没有意义...");
            return;
        }
        // 核心思想：利用一个临时节点，然后遍历所有节点插入到这个临时节点的前面，这样实现链表翻转
        Node<T> cur = head, next, temp = new Node<>(null);
        while (cur != null) {
            next = cur.next; // 存放当前节点的下一个节点，后续移动指针使用
            cur.next = temp.next; // 相当于将临时节点插入到当前节点的前面
            temp.next = cur; // 然后将连接的节点拼接到新节点上
            cur = next; // 后移
        }
        // 由于temp自身只是做中转，不存储值，所以赋值只能是temp的next，否则会多出一个节点，那就是temp这个空节点
        head = temp.next;
    }

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
        addFirst(value);
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
        }
        size++;
    }

    public void addFirst(T value) {
        final Node<T> newNode = new Node<>(value);
        Node<T> h = head;
        if (h == null) {
            head = newNode;
        } else {
            if (h.next != null) {
                newNode.next = h.next;
                h.next = newNode;
            } else {
                h.next = newNode;
            }
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
            // 为什么while里面的循环条件是大于1而不是大于0
            // 原因：单链表没有上一个节点的说法，只有下一个节点，所以只能找到索引位置的前一个元素才能替换索引位置的元素
            Node<T> n = head;
            do {
                n = n.next;
                index--;
            } while (index > 1);
            final Node<T> newNode = new Node<>(value);
            newNode.next = n.next;
            n.next = newNode;
            size++;
        }
    }

    public void remove(T value) {
        if (value == null) {
            return;
        }
        Node<T> n = head;
        Node<T> p = head;
        do {
            if (value.equals(n.item)) {
                if (n == head) {
                    head = n.next;
                } else {
                    // 删除当前节点则：当前节点的上一个节点的next 指向 当前节点的next 当前节点没有被引用指向会自动被GC回收
                    p.next = n.next;
                }
                size--;
                return;
            }
            // 保存上一个节点
            p = n;
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

    public int size() {
        return size;
    }

    class Node<T> {

        private T item;
        private Node<T> next;

        Node(T item) {
            this.item = item;
        }
    }
}
