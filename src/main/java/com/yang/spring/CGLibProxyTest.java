package com.yang.spring;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.util.Arrays;

/**
 * cglib 代理的坑
 * 假设有个类A，会在字节码的层面上动态生成一个类B并加载进JVM里面。B继承自A同时又有对A的引用，B会重写所有的A类里面的非Final、非private方法，
 * 从而可以在目标方法调用前后进行对应的增强了。
 * 本文中：demo.setAge(10);执行的是代理对象的setAge()方法，所以set进去的值是给了代理对象的，目标对象仍然我null。
 * 而我们findAge()方法因为我标注了final，因此不能被CGLIB代理，所以只能从目标对象里拿值。因此它也只能拿到null，而我们调用的getAge()方法它被代理过，所以他能拿到正确的值：10。
 */
public class CGLibProxyTest {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new DemoT());
        proxyFactory.addAdvice((MethodBeforeAdvice) (method, args1, target) -> {
                    System.out.println("你被拦截了：方法名为：" + method.getName() + " 参数为--" + Arrays.asList(args1));
                }
        );

        DemoT demo = (DemoT) proxyFactory.getProxy();
        //你被拦截了：方法名为：setAge 参数为--[10]
        demo.setAge(10);

        //你被拦截了：方法名为：getAge 参数为--[]
        System.out.println(demo.getAge()); //10
        System.out.println(demo.age); //null 对你没看错，这里是null
        System.out.println(demo.findAge()); //null 对你没看错，这里是null
    }

    // 不实现接口,就会用CGLIB去代理
    static class DemoT {
        public Integer age;

        // 此处用final修饰了  CGLIB也不会代理此方法了
        public final Integer findAge() {
            return age;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
