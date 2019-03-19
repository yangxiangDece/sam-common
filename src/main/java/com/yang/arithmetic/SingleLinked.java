package com.yang.arithmetic;

/**
 *
 */
public class SingleLinked<E> {

    private Node<E> first;

    public SingleLinked() {
    }

    private void add(E e) {
        final Node<E> f = first;
        first = new Node<>(e,f);
    }

    private static class Node<E> {

        E value;

        Node<E> next;

        public Node(E value,Node<E> next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
