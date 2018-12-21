package com.yang.design.mode.command;

/**
 * 关闭电灯的命令
 *
 * 利用Command接口，每个动作都被实现成一个简单的命令对象。命令对象持有对一个厂商类的实例的引用，并实现了一个execute()方法。
 * 这个方法会调用厂商类实例的一个或多个方法，完成特定的行为。在这个例子中，有两个类，分别打开电灯与关闭电灯
 */
public class LightOffCommand implements Command {

    private Light light;

    //构造器被传入了某个电灯，以便让这个命令控制，然后记录在实例变量中，一旦调用execute()，就由这个电灯对象成为接收者，负债接收请求。
    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}