package com.yang.design.mode.iterator;

import java.util.ArrayList;
import java.util.List;

public class PancakeHouseMenu {
    private List<MenuItem> menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList<>();
        addItems("PancakeHouseMenu name1","PancakeHouseMenu description1",true,2.99);
        addItems("PancakeHouseMenu name2","PancakeHouseMenu description2",false,3.45);
        addItems("PancakeHouseMenu name3","PancakeHouseMenu description3",true,2.34);
        addItems("PancakeHouseMenu name4","PancakeHouseMenu description4",true,5.19);
    }

    public void addItems(String name, String description, boolean vegetarian, double price){
        menuItems.add(new MenuItem(name,description,vegetarian,price));
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public MyIterator createIterator(){
        return new PancakeHouseMenuIterator(menuItems);
    }
}
