package com.yang.designmode.headfirst.adapter;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 因为我们将枚举适配成迭代器，适配器需要实现迭代器接口，这里的适配器必须看起来像一个迭代器
 */
public class EnumerationIterator implements Iterator {

    Enumeration enumeration;

    //利用组合的方式，将枚举结合进入适配器中，所以用一个实例变量记录枚举
    public EnumerationIterator(Enumeration enumeration) {
        this.enumeration = enumeration;
    }

    //迭代器的hasNext()方法其实是委托给枚举的hasMoreElements()方法
    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public Object next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer action) {
    }
}
