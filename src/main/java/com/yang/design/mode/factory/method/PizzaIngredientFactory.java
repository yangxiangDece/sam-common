package com.yang.design.mode.factory.method;

import com.yang.design.mode.factory.method.material.Cheese;
import com.yang.design.mode.factory.method.material.Clams;
import com.yang.design.mode.factory.method.material.Dough;
import com.yang.design.mode.factory.method.material.Sauce;

/**
 * 比萨抽象工厂接口，所有比萨工厂都必须实现这个接口
 * 如果每个工厂实例内都有某一种通用的机制，需要实现，就可以把这个例子改写成抽象类
 */
public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Clams createClams();
}
