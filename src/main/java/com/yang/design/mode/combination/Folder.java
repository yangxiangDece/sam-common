package com.yang.design.mode.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite（容器构件）：它在组合结构中表示容器节点对象，容器节点包含子节点，其子节点可以是叶子节点，也可以是容器节点，
 * 它提供一个集合用于存储子节点，实现了在抽象构件中定义的行为，包括那些访问及管理子构件的方法，在其业务方法中可以递归调用其子节点的业务方法。
 */
public class Folder extends AbstractFile {

    private List<AbstractFile> list=new ArrayList<>();
    private String name;

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public void add(AbstractFile file) {
        list.add(file);
    }

    @Override
    public void remove(AbstractFile file) {
        list.remove(file);
    }

    @Override
    public AbstractFile getChild(int i) {
        return list.get(i);
    }

    @Override
    public void killVirus() {
        System.out.println("*******开始对文件夹：" + name + "进行杀毒");
        //递归调用成员构件的killVirus()方法
        list.forEach(AbstractFile::killVirus);
    }
}
