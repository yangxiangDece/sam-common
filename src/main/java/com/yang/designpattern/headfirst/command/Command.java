package com.yang.designpattern.headfirst.command;

/**
 * 所有遥控器命令都实现这个Command接口，此接口包含一个方法，execute()，命令封装了某个特定厂商类的一组动作，遥控器可以通过调用execute()方法，执行这些动作，而不需要关系具体如何执行。
 */
public interface Command {

    void execute();

    /**
     * 当命令支持撤销操作时，该命令就必须提供与execute()方法相反的undo()方法。不管execute()刚才做什么，undo()都会倒转过来。
     * <p>
     * 如何能够实现多层次的撤销操作？换句话说，我希望能够按下撤销操作按钮许多次，撤销操作到很早很早以前的状态
     * 不要只是记录最后一次被执行的命令，而使用一个堆栈记录操作过程的每一个命令，然后，不管什么时候按下了撤销按钮，你都可以从堆栈中取出最上层的命令，然后调用它的undo()方法
     */
    void undo();
}
