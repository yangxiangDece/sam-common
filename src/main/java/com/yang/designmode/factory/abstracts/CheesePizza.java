package com.yang.designmode.factory.abstracts;

/**
 * 具体产品类
 */
public class CheesePizza extends Pizza {

    private PizzaIngredientFactory pizzaIngredientFactory;

    //要制造比萨，需要工厂提供原料。所以每个比萨类都需要从构造器参数中得到一个工厂，并将这个工厂存储在一个实例变量中
    public CheesePizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    /**
     * prepare()方法一步一步地创建芝士比萨，每当需要原料时，就像工厂要。
     * Pizza的代码利用相关的工厂生产原料，所生产的原料依赖所使用的工厂，Pizza类根本不关心这些原料，它只知道如何制作比萨。
     * 现在，Pizza和区域原料之间解耦了，无论原料工厂是在落基山脉还是西北沿海岸地区，Pizza类都可以轻易地复用。
     */
    @Override
    void prepare() {
        System.out.println("Preparing " + name);
        dough = pizzaIngredientFactory.createDough();
        sauce = pizzaIngredientFactory.createSauce();
        cheese = pizzaIngredientFactory.createCheese();
    }
}
