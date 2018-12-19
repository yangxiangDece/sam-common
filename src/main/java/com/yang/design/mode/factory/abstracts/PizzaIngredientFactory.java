package com.yang.design.mode.factory.abstracts;

import com.yang.design.mode.factory.abstracts.material.Cheese;
import com.yang.design.mode.factory.abstracts.material.Clams;
import com.yang.design.mode.factory.abstracts.material.Dough;
import com.yang.design.mode.factory.abstracts.material.Sauce;

/**
 * AbstractFactory 类
 *
 * 抽象工厂定义了一个接口，所有具体工厂都必须实现此接口，这个接口包含一组方法用来生产产品。
 *
 * 比萨抽象工厂接口，所有比萨工厂都必须实现这个接口
 * 如果每个工厂实例内都有某一种通用的机制，需要实现，就可以把这个例子改写成抽象类
 */
public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Clams createClams();
}
