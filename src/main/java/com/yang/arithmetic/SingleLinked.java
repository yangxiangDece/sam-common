package com.yang.arithmetic;

/**
 *
 */
public class SingleLinked<E> {

    private Linked<E> first;

    public SingleLinked() {
    }

    private void addFirst(E e) {
        first.next = e;
    }

    private static class Linked<E> {

        E value;

        public E next;

        public Linked(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Linked{" +
                    "value=" + value +
                    '}';
        }
    }
}
