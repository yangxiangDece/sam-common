package com.yang.dubbo.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;

public class DubboSPITest {

    public static void main(String[] args) {
//        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
//        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
//        optimusPrime.sayHello();
//
//        Robot bumblebee = extensionLoader.getExtension("bumblebee");
//        bumblebee.sayHello();

        ExtensionLoader<Protocol> extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol protocol = extensionLoader.getExtension("hessian");
    }
}
