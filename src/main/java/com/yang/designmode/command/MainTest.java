package com.yang.designmode.command;

/**
 * 这个是命令模式的客户端
 */
public class MainTest {

    public static void main(String[] args) {
        //1、遥控器就是调用者，会传入一个命令对象，可以发出请求
        SimpleRemoteControl remoteControl = new SimpleRemoteControl();
        //2、现在创建了一个电灯对象，此对象也就是请求的接收者
        Light light = new Light();
        //3、在这里创建一个具体的命令，然后接收者传给它
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        //4、将命令传给调用者
        remoteControl.setSlot(lightOnCommand);
        //5、模拟按下按钮，也就是调用者开始调用
        remoteControl.buttonWasPressed();
        remoteControl.undoButtonWasPressed();

        // 关闭电灯
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        remoteControl.setSlot(lightOffCommand);
        remoteControl.buttonWasPressed();
        remoteControl.undoButtonWasPressed();
    }
}
