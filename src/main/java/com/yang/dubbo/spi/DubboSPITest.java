package com.yang.dubbo.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class DubboSPITest {

    public static void main(String[] args) {
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();

        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }
}
