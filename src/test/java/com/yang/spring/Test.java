package com.yang.spring;

import sun.misc.Launcher;

import java.net.URL;

public class Test {

    public static void main(String[] args) throws Exception {

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        System.out.println(contextClassLoader.getParent());
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());

        // 获得根类加载器所加载的核心类库,并会看到本机安装的Java环境变量指定的jdk中提供的核心jar包路径
        for (URL url : Launcher.getBootstrapClassPath().getURLs()) {
            System.out.println(url.toExternalForm());
        }
    }
}