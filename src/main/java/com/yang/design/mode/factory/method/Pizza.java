package com.yang.design.mode.factory.method;

import com.yang.design.mode.factory.method.material.Cheese;
import com.yang.design.mode.factory.method.material.Clams;
import com.yang.design.mode.factory.method.material.Dough;
import com.yang.design.mode.factory.method.material.Sauce;

public abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Cheese cheese;
    Clams clams;

    abstract void prepare();

    void bake(){
        System.out.println("Bake for 25 minutes at 50");
    }

    void cut(){
        System.out.println("Cutting the pizza into diagonal slices");
    }

    void box(){
        System.out.println("Place pizza in official PizzaStore box");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
