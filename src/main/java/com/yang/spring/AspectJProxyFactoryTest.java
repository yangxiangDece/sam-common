package com.yang.spring;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class AspectJProxyFactoryTest {

    public static void main(String[] args) {
        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(new HelloServiceImpl());
        aspectJProxyFactory.addAspect(AspectJProxyTest.class);

        HelloService proxy = aspectJProxyFactory.getProxy();
        proxy.hello();

        System.out.println(proxy);
        System.out.println(proxy.getClass());
    }

    @Aspect // 切面（Aspect） 切面由切点和增强(或引介)组成，或者只由增强（或引介）实现。
    private static class AspectJProxyTest {

        // 切点（PointCut） 一个项目中有很多的类，一个类有很多个连接点，当我们需要在某个方法前插入一段增强（advice）代码时，
        // 我们就需要使用切点信息来确定，要在哪些连接点上添加增强。 方法名是否匹配、类是否是某个类、以及子类等。
        @Pointcut("execution(* hello(..))")
        private void beforeAdd() {
        }

        // 连接点（JoinPoint） 连接点就是程序执行的某个特定的位置，如：类开始初始化前、类初始化后、类的某个方法调用前、类的某个方法调用后、方法抛出异常后等。
        // Spring 只支持类的方法前、后、抛出异常后的连接点。
        @Before("beforeAdd()")
        public void before() {
            // 增强（Advice）  AOP（切面编程）是用来给某一类特殊的连接点，添加一些特殊的功能，那么我们添加的功能也就是增强。
            System.out.println("before....");
        }

        // 连接点（JoinPoint） 连接点就是程序执行的某个特定的位置，如：类开始初始化前、类初始化后、类的某个方法调用前、类的某个方法调用后、方法抛出异常后等。
        // Spring 只支持类的方法前、后、抛出异常后的连接点。
        @After("beforeAdd()")
        public void after() {
            // 增强（Advice）  AOP（切面编程）是用来给某一类特殊的连接点，添加一些特殊的功能，那么我们添加的功能也就是增强。
            System.out.println("after...");
        }
    }

    private interface HelloService {
        void hello();
    }

    private static class HelloServiceImpl implements HelloService {

        public void hello() {
            System.out.println("hello...");
        }
    }
}
