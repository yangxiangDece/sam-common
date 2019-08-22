package com.yang.dubbo.spi;

import org.apache.dubbo.common.compiler.Compiler;
import org.apache.dubbo.common.compiler.support.JavassistCompiler;
import org.apache.dubbo.common.extension.AdaptiveClassCodeGenerator;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class DubboSPITest {

    public static void main(String[] args) throws Exception {
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();

        ExtensionLoader<Protocol> loader = ExtensionLoader.getExtensionLoader(Protocol.class);
        System.out.println(loader.getExtension("dubbo"));

//        generateProxy();
    }

    private static void generateProxy() throws Exception {
        // 如果没有指定的 adaptive就会 生成一个代理类
        String code = new AdaptiveClassCodeGenerator(Protocol.class, "dubbo").generate();
        System.out.println(code);
//        Compiler compiler = new JdkCompiler();
        Compiler compiler = new JavassistCompiler();
        Class<?> protocolClass = compiler.compile(code, Thread.currentThread().getContextClassLoader());
        Object o = protocolClass.newInstance();
        System.out.println(o);
    }

    private static String getSetterProperty(Method method) {
        return method.getName().length() > 3 ? method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4) : "";
    }

    private static boolean isSetter(Method method) {
        return method.getName().startsWith("set")
                && method.getParameterTypes().length == 1
                && Modifier.isPublic(method.getModifiers());
    }
}
