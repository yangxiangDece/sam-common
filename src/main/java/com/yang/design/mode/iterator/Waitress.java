package com.yang.design.mode.iterator;

public class Waitress {
    private PancakeHouseMenu pancakeHouseMenu;
    private DinerMenu dinerMenu;

    public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public void printMenu(){
        MyIterator pancakeIterator=pancakeHouseMenu.createIterator();
        MyIterator dinerMenuIterator=dinerMenu.createIterator();
        System.out.println("Menu\n-----\nBreakFast");
        printMenu(pancakeIterator);
        System.out.println("\nlunch");
        printMenu(dinerMenuIterator);
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
