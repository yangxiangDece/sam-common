package com.yang.design.mode.combination;

/**
 * Leaf（叶子构件）：它在组合结构中表示叶子节点对象，叶子节点没有子节点，它实现了在抽象构件中定义的行为。对于那些访问及管理子构件的方法，可以通过异常等方式进行处理。
 */
public class ImageFile extends AbstractFile {

    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    @Override
    public void add(AbstractFile file) {
        System.out.println("ImageFile 不支持add方法..");
    }

    @Override
    public void remove(AbstractFile file) {
        System.out.println("ImageFile 不支持remove方法..");
    }

    @Override
    public AbstractFile getChild(int i) {
        System.out.println("ImageFile 不支持getChild方法..");
        return null;
    }

    @Override
    public void killVirus() {
        System.out.println("模拟，对图像文件："+name+"进行杀毒");
    }
}
