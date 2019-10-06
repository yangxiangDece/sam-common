package com.yang.designmode.template;

/**
 * 模板方法模式
 * CaffeineBeverage类专注在算法本身，而由子类提供完整的实现，算法只存在于一个地方，方便修改，
 * 这个模板方法提供了一个框架，可以让其他的咖啡因饮料插进来，新的咖啡因饮料只需要实现自己的方法就可以了。
 */
public abstract class CaffeineBeverage {

    //通过prepareRecipe()方法来处理茶和咖啡。这个方法被声明为final，因为我们不希望子类覆盖这个方法
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    //因为咖啡和茶处理这些方法的做法不同，所以这两个方法必须被声明为抽象，剩余的东西留给子类去实现
    abstract void brew();

    abstract void addCondiments();

    public void boilWater() {
        System.out.println("Boiling water");
    }

    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    /**
     * 定义一个方法，这个方法永远只会返回true，这就是钩子方法，子类可以覆盖这个方法
     * <p>
     * 钩子的几种用法
     * 1、钩子可以让子类实现算法中可选部分
     * 2、让子类能够有机会对模板方法中某些即将发生的步骤做出反应
     */
    public boolean customerWantsCondiments() {
        return true;
    }
}
