package com.yang.design.mode.combination;

public class MainTest {

    public static void main(String[] args) {
        AbstractFile folder=new Folder("张三的资料");
        AbstractFile imageFolder=new Folder("图像文件");
        AbstractFile videoFolder=new Folder("视频文件");
        AbstractFile textFolder=new Folder("文本文件");

        AbstractFile imageFile1=new ImageFile("图像1.jpg");
        AbstractFile imageFile2=new ImageFile("图像2.gif");

        AbstractFile textFile1=new TextFile("文本1.text");
        AbstractFile textFile2=new TextFile("文本2.text");

        AbstractFile videoFile1=new VideoFile("视频1.rmvb");
        AbstractFile videoFile2=new VideoFile("视频2.rmvb");

        imageFolder.add(imageFile1);
        imageFolder.add(imageFile2);
        videoFolder.add(videoFile1);
        videoFolder.add(videoFile2);
        textFolder.add(textFile1);
        textFolder.add(textFile2);

        folder.add(imageFolder);
        folder.add(videoFolder);
        folder.add(textFolder);

        //从“张三的资料”节点开始进行递归杀毒操作
        folder.killVirus();
    }
}
