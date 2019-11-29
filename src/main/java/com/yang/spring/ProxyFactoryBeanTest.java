package com.yang.spring;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * Advice:通知，定义在连接点做什么，比如我们在方法前后进行日志打印（前置通知、后置通知、环绕通知等等）
 * <p>
 * PointCut：切点，决定advice应该作用于那个连接点，比如根据正则等规则匹配哪些方法需要增强（Pointcut 目前有getClassFilter（类匹配），
 * getMethodMatcher（方法匹配），Pointcut TRUE （全匹配））
 * <p>
 * JoinPoint：连接点，就是spring允许你是通知（Advice）的地方，那可就真多了，基本每个方法的前、后（两者都有也行），或抛出异常是时都可以是连接点，
 * spring只支持方法连接点。其他如AspectJ还可以让你在构造器或属性注入时都行，不过那不是咱们关注的，只要记住，和方法有关的前前后后都是连接点（通知方法里
 * 都可以获取到这个连接点，顺便获取到相关信息）。Spring 只支持类的方法前、后、抛出异常后的连接点。
 * <p>
 * Advisor：把pointcut和advice连接起来（可由Spring去完成，我们都交给容器管理就行，当然，你也可以手动完成）Spring的Advisor是Pointcut和Advice的配置器，
 * 它是将Advice注入程序中Pointcut位置的代码。org.springframework.aop.support.DefaultPointcutAdvisor是最通用的Advisor类
 * 就是我们定义了标识@Aspect注解的类，这个类就是用来切面处理的
 */
public class ProxyFactoryBeanTest {

    public static void main(String[] args) {
        // 定义一个切点表达式
        String pointcutExpression = "execution( int com.yang.spring.ProxyFactoryBeanTest.Person.run() )";

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new Person());

        //声明一个aspectj切点
        AspectJExpressionPointcut cut = new AspectJExpressionPointcut();
        cut.setExpression(pointcutExpression);

        // 声明一个通知（此处使用环绕通知 MethodInterceptor ）
        Advice advice = (MethodInterceptor) invocation -> {
            System.out.println("============>执行前拦截...");
            Object obj = invocation.proceed();
            System.out.println("============>执行后拦截...");
            return obj;
        };

        // 切面 = 切点 + 通知
        Advisor advisor = new DefaultPointcutAdvisor(cut, advice);
        proxyFactoryBean.addAdvisor(advisor);

        Person person = (Person) proxyFactoryBean.getObject();

        person.run();
        person.run(100);
        person.say();
        person.sayHi("jack");
        person.say("tom", 66);

        System.out.println(person);
        System.out.println(person.getClass());
    }

    private static class Person {

        public int run() {
            System.out.println("我在run...");
            return 0;
        }

        public void run(int i) {
            System.out.println("我在run...<" + i + ">");
        }

        public void say() {
            System.out.println("我在say...");
        }

        public void sayHi(String name) {
            System.out.println("Hi," + name + ",你好");
        }

        public int say(String name, int i) {
            System.out.println(name + "----" + i);
            return 0;
        }
    }
}
