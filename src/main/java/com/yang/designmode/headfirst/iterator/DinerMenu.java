package com.yang.designmode.headfirst.iterator;

public class DinerMenu implements CreateIterator {

    private static final int MAX_ITEMS = 4;
    private int numberOfItems = 0;
    private MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        addItems("DinerMenu name1", "DinerMenu description1", true, 3.56);
        addItems("DinerMenu name2", "DinerMenu description2", false, 2.25);
        addItems("DinerMenu name3", "DinerMenu description3", false, 1.96);
        addItems("DinerMenu name4", "DinerMenu description4", true, 2.42);
    }

    public void addItems(String name, String description, boolean vegetarian, double price) {
        if (numberOfItems >= MAX_ITEMS) {
            System.out.println("Sorry, menu is full! Can't add item to menu");
        } else {
            menuItems[numberOfItems++] = new MenuItem(name, description, vegetarian, price);
        }
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    @Override
    public MyIterator createIterator() {
        return new DinerMenuIterator(menuItems);
    }
}
