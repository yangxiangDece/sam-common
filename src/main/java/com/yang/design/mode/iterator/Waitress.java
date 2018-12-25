package com.yang.design.mode.iterator;

/**
 * 这里运用了迭代器模式和组合模式
 */
public class Waitress {
    private CreateIterator[] iterators;

    public Waitress(CreateIterator... iterators) {
        this.iterators = iterators;
    }

    public void printMenu(){
        for (CreateIterator iterator:iterators) {
            printMenu(iterator.createIterator());
        }
    }

    private void printMenu(MyIterator iterator){
        while (iterator.hasNext()) {
            MenuItem item= (MenuItem) iterator.next();
            System.out.println(item.getName()+",");
            System.out.println(item.getPrice()+" -- ");
            System.out.println(item.getDescription());
        }
    }
}
