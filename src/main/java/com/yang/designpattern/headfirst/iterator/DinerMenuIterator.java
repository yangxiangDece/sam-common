package com.yang.designpattern.headfirst.iterator;

public class DinerMenuIterator implements MyIterator {

    private MenuItem[] items;
    private int position = 0;

    public DinerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return (position < items.length && items[position] != null);
    }

    @Override
    public Object next() {
        return items[position++];
    }
}
