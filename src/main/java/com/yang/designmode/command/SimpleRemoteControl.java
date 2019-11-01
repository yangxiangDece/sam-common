package com.yang.designmode.command;

/**
 * 管理一组命令对象，每个按钮都有一个命令对象。每当按下按钮，就调用相应的xxButtonWasPressed()方法，间接成该命令的execute()方法被调用
 */
public class SimpleRemoteControl {

    // 有一个插槽持有命令，而这个命令控制着一个装置
    private Command slot;

    // 记录前一个命令，以便在撤销的时候调用
    private Command undoCommand;

    public SimpleRemoteControl() {
    }

    // 这个方法用来设置插槽控制的命令，如果这段代码的客户想要改变遥控器按钮的行为，可以多次调用这个方法。
    public void setSlot(Command slot) {
        this.slot = slot;
        undoCommand = slot;
    }

    // 当按下这个按钮时，这个方法就会调用，使得当前命令衔接插槽，并调用它execute()方法
    public void buttonWasPressed() {
        slot.execute();
    }

    // 撤销操作
    public void undoButtonWasPressed() {
        undoCommand.undo();
    }
}
