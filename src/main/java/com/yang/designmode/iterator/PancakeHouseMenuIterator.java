package com.yang.designmode.iterator;

import java.util.List;

public class PancakeHouseMenuIterator implements MyIterator {

    private List<MenuItem> list;
    private int position=0;

    public PancakeHouseMenuIterator(List<MenuItem> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return (position<list.size() && list.get(position)!=null);
    }

    @Override
    public Object next() {
        return list.get(position++);
    }
}
