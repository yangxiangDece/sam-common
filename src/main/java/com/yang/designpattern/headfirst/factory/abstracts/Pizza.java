package com.yang.designpattern.headfirst.factory.abstracts;

import com.yang.designpattern.headfirst.factory.abstracts.material.Cheese;
import com.yang.designpattern.headfirst.factory.abstracts.material.Clams;
import com.yang.designpattern.headfirst.factory.abstracts.material.Dough;
import com.yang.designpattern.headfirst.factory.abstracts.material.Sauce;

/**
 * 抽象产品类
 */
public abstract class Pizza {

    protected String name;
    protected Dough dough;
    protected Sauce sauce;
    protected Cheese cheese;
    Clams clams;

    abstract void prepare();

    void bake() {
        System.out.println("Bake for 25 minutes at 50");
    }

    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
