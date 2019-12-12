package com.yang.designpattern.headfirst.combination;

/**
 * Component（抽象构件）：它可以是接口或抽象类，为叶子构件和容器构件对象声明接口，在该角色中可以包含所有子类共有行为的声明和实现。
 * 在抽象构件中定义了访问及管理它的子构件的方法，如增加子构件、删除子构件、获取子构件等。
 * <p>
 * 组合模式的关键是定义了一个抽象构件类，它既可以代表叶子，又可以代表容器，而客户端针对该抽象构件类进行编程，无须知道它到底表示的是叶子还是容器，可以对其进行统一处理。
 * 同时容器对象与抽象构件类之间还建立一个聚合关联关系，在容器对象中既可以包含叶子，也可以包含容器，以此实现递归组合，形成一个树形结构。
 * <p>
 * 如果不使用组合模式，客户端代码将过多地依赖于容器对象复杂的内部实现结构，容器对象内部实现结构的变化将引起客户代码的频繁变化，带来了代码维护复杂、可扩展性差等弊端。
 * 组合模式的引入将在一定程度上解决这些问题。
 */
public class MainTest {

    public static void main(String[] args) {

        AbstractFile folder = new Folder("张三的资料");

        AbstractFile imageFolder = new Folder("图像文件");
        AbstractFile videoFolder = new Folder("视频文件");
        AbstractFile textFolder = new Folder("文本文件");

        imageFolder.add(new ImageFile("图像1.jpg"));
        imageFolder.add(new ImageFile("图像2.gif"));

        videoFolder.add(new VideoFile("视频1.rmvb"));
        videoFolder.add(new VideoFile("视频1.rmvb"));

        textFolder.add(new TextFile("文本1.text"));
        textFolder.add(new TextFile("文本2.text"));

        folder.add(imageFolder);
        folder.add(videoFolder);
        folder.add(textFolder);

        //从“张三的资料”节点开始进行递归杀毒操作
        folder.killVirus();
    }
}
