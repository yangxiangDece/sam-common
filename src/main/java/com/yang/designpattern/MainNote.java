package com.yang.designpattern;

/*
    七大原则
        单一职责原则
        接口隔离原则
            看看spring的接口就明白 接口拆的很细，类可以实现多个接口，可以按照自己的业务来实现不同的多个接口
        依赖倒转(倒置)原则
            高层模块不应该依赖底层模块，二者都应该依赖其抽象
            抽象不应该依赖细节，细节应该依赖抽象
            核心思想：面向接口编程
            使用接口或抽象类的目的是指定好规范，而不涉及任何具体的操作，把展现细节的任务交给他们的实现类去完成
            底层模块尽量都要有抽象类或接口，或者两者都有，程序稳定性更好
            变量的声明类型尽量是抽象类或接口，这样我们的变量引用和实际对象间，就存在一个缓冲层，利于程序扩展和优化
            两种方式：
                1、通过接口方式传递  void play(IPlay play); 这是接口中的一个方法，方法中再传入接口
                2、通过构造方法传递  private IPlay play;  这是类中的一个属性
        里氏替换原则
            继承实际上让两个类的耦合性增强了，在适当的情况下通通过组合来替换继承
        开闭原则 ocp
            对扩展开放，对修改关闭；用抽象构建框架，用实现扩展细节
        迪米特法则
            只与朋友通信；成员变量、方法参数、方法返回值中的类称为直接的朋友，而出现在局部变量的类不是直接的朋友，陌生的类最好不要以局部变量表的形式出现在类的内部
            被依赖的类不管有多复杂，都尽量封装在类的内部，对外除了提供的public方法，不对外泄露任何信息
        合成复用原则
            尽量使用合成/聚合的方式，而不是使用继承
 */

/*
    类之间的关系：依赖、泛化（继承）、实现、关联、聚合和组合
        依赖
        泛化（继承）
        实现
        关联
        聚合和组合
 */

/*
 *
 * 设计模式：
 *      1、单例模式：
 *          确保一个类只有一个实例，并提供一个全局访问点
 *          推荐使用：静态内部类、枚举
 *          JDK中的RunTime类使用的是饿汉模式
 *      2、工厂方法模式：
 *          定义了一个创建对象的接口，但由子类绝对要实例化的类是哪一个。工厂方法让类把实例化推迟到子类。
 *          工厂方法使用继承，把对象的创建委托给子类，子类实现工厂方法来创建对象
 *      3、抽象工厂模式：
 *          提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类
 *          抽象工厂使用对象组合，对象的创建被实现在工厂接口所暴露出来的方法中
 *          不要让类继承具体类，而是继承一个抽象类或是实现接口
 *          不要覆盖基类中已经实现的方法
 *      4、原型模式：
 *          用原型实例指定创建对象的种类，并且通过拷贝这些原型，创建新的对象
 *          通过将一个原型对象传递给那个发动创建的对象，这个要发动创建的对象通过请求原型对象拷贝它们自己来实施创建，即 对象.clone()
 *      5、建造者模式：
 *          将复杂对象的建造过程抽象出来，使这个抽象过程的不同实现方法可以构造出不同表现的对象
 *          是一步一步创建一个复杂的对象，它允许用户只通过指定复杂对象的类型和内容就可以构建它们，用户不需要知道内部的具体构建细节
 *      6、适配器模式：
 *          将一个类的接口，转换成客户期望的另一个接口。适配器让原本接口不兼容的类可以合作无间。
 *          适配器实现目标接口，并持有被适配器者的实例
 *          对象适配器：采用组合的形式，将src作为一个对象，持有
 *          接口适配器：以接口形式，将src作为一个接口，实现
 *          类适配器：采用继承的形式，需要多重继承才能实现它，在java中无法实现。
 *      7、桥接模式：
 *          将实现与抽象放在两个不同的类层次中，使两个层次可以独立改变
 *      8、装饰模式：
 *          动态地将责任附加到对象上，若要扩展功能，装饰者提供了比继承更有弹性的替代方案。
 *          装饰者可以在所委托的被装饰者的行为之前/或之后，加上自己的行为，以达到特定的目的。
 *      9、组合模式：
 *          允许你将对象组合成树形结构来表现“整体/部分”层次结构。组合能让客户以一致的方式处理个别对象以及对象组合。
 *          使用组合结构，我们把相同的操作应用在组合和个别对象上。换句话说，在大多数情况下，我们可以忽略对象组合和个别对象之间的差别。
 *          组合模式的关键是定义了一个抽象构件类，它既可以代表叶子，又可以代表容器，而客户端针对该抽象构件类进行编程，无须知道它到底表示的是叶子还是容器，可以对其进行统一处理。
 *          同时容器对象与抽象构件类之间还建立一个聚合关联关系，在容器对象中既可以包含叶子，也可以包含容器，以此实现递归组合，形成一个树形结构。
 *          如果不使用组合模式，客户端代码将过多地依赖于容器对象复杂的内部实现结构，容器对象内部实现结构的变化将引起客户代码的频繁变化，带来了代码维护复杂、可扩展性差等弊端。
 *          组合模式的引入将在一定程度上解决这些问题。
 *          组合结构内的任意对象称为组件，组件可以是组合，也可以是叶节点。
 *      10、外观模式：
 *          提供了一个统一的接口，用来访问子系统中的一群接口。外观定义了一个高层接口，让子系统更容易使用。
 *          注意：请务必记得外观模式的意图：要提供一个简单的接口，好让子系统更易于使用。
 *          外观不只是简化接口，也将客户从组件的子系统中解耦
 *          外观和适配器可以包装许多类，但是外观的意图是简化接口，而适配器的意图是将接口转换成不同的接口
 *      11、享元模式：
 *          共享对象，类似数据源、缓冲池，比如String常量池、数据库连接池等
 *          减少了对象的创建，降低了程序内存的占用，提高了效率
 *          但是提高了系统的复杂度。需要分离出内部状态和外部状态，而外部状态具有固化特性，不应该随着内部的改变而改变。
 *      12、代理模式：
 *          为另一个对象提供一个替身或占位符以控制对这个对象的访问。使用代理模式创建代表对象，让代表对象控制某对象的访问，被代理的对象可以是远程的对象、
 *          创建开销大的对象或需要安全控制的对象。主要作用是：控制访问
 *          动态代理：运行时才将类创建出来，代码开始执行时，还没有proxy类，它是根据需要从你传入的接口集创建的
 *      13、模板方法模式：
 *          在一个方法中定义了一个算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以在不改变算法结构的情况下，重新定义算分中的某些步骤
 *          这个方法将算分定义成一组步骤，其中的任何步骤都可以是抽象的，由子类负债实现。这可以确保算法的结构保持不变，自己可以实现一些公共方法，也可以由子类提供部分实现。
 *      14、命令模式：
 *          将请求封装成对象，以便使用不同的请求、队列或者日志来参数化其他对象。命令模式也支持可撤销的操作。
 *      15、访问者模式：
 *      16、迭代器模式：
 *          提供一种方法顺序访问一个聚合对象中的各个元素，而又不暴露其内部的表示。集合也被称为聚合(aggregate)
 *          迭代器模式让我们游走于集合内的每一个元素，而又不暴露其内部的表示。把游走的任务放在迭代器上，而不是集合上。这样简化了集合的接口和实现，也让责任各得其所。
 *      17、观察者模式：
 *          定义了对象之间的一对多依赖，这样一来，当一个对象改变状态时，它的所有依赖者都会收到通知并自动更新。
 *          com.yang.designpattern.headfirst.observer.myself包：自定义的观察者模式
 *          com.yang.designpattern.headfirst.observer.jdk包：Java jdk自带的观察者模式(Observer(观察者)接口和Observable(可观察者)类)
 *      18、中介者模式：
 *      19、备忘录模式：
 *      20、解释器模式：
 *      21、状态模式：
 *           允许对象在内部状态改变时改变它的行为，对象看起来好像改变了它的类。
 *      22、策略模式：
 *           定义了算法族，分别封装起来，让它们之间可以互相替换，此模式让算法的变化独立于使用算法的客户。
 *      23、责任链模式：
 *           避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止。
 *      24、复合模式：
 *          MVC是复合模式，结合了观察者模式、策略模式和组合模式
 *          模型使用观察者模式，以便观察者更新，同时保持两者之间的解耦
 *          控制器是视图的策略，视图可以使用不同的控制器实现，得到不同的行为
 *          视图使用组合模式实现，用户界面通常组合了嵌套的组件，像面板、框架和按钮。
 *
 *      一句话：
 *          装饰者模式：包装一个对象，以提供新的行为。
 *          状态模式：封装了基于状态的行为，并使用委托在行为之间切换。
 *          迭代器模式：在对象的集合之中游走，而不暴露集合的实现。
 *          外观模式：简化一群类的接口。
 *          策略模式：封装可以互换的行为，并使用委托来决定要使用哪一个。
 *          代理模式：包装对象，以控制对此对象的访问。
 *          工厂方法模式：由子类决定要创建的具体类是哪一个。
 *          适配器模式：封装对象，并提供不同的接口。
 *          观察者模式：让对象能够在状态改变时被通知。
 *          模板方法模式：由子类决定如何实现一个算法中的步骤。
 *          组合模式：客户用一致的方式处理对象集合和单个对象。
 *          单件模式：确保有且只有一个对象被创建。
 *          抽象工厂模式：允许客户创建对象的家族，而无需指定他们的具体类。
 *          命令模式：封装请求成为对象。
 *     分类：
 *          创建型模式：工厂方法、抽象工厂、单例、原型、建造者
 *          结构型模式：装饰、代理、外观、组合、适配器、桥接、享元
 *          行为型模式：责任链、命令、迭代器、观察者、状态、策略、模板
 */
public class MainNote {
    public static void main(String[] args) {

    }
}
