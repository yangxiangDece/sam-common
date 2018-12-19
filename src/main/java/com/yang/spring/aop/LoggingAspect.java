package com.yang.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


/**
 * AOP的7个专业术语：
 *  1、增强（Advice）:
 *      AOP（切面编程）是用来给某一类特殊的连接点，添加一些特殊的功能，那么我们添加的功能也就是增强。
 *  2、切点（PointCut）：
 *      一个项目中有很多的类，一个类有很多个连接点，当我们需要在某个方法前插入一段增强（advice）代码时，我们就需要使用切点信息来确定，要在哪些连接点上添加增强。
 *      如果把连接点当做数据库中的记录，那么切点就是查找该记录的查询条件。
 *      所以，一般我们要实现一个切点时，那么我们需要判断哪些连接点是符合我们的条件的，如：方法名是否匹配、类是否是某个类、以及子类等。
 *  3、连接点（JoinPoint）：
 *      连接点就是程序执行的某个特定的位置，如：类开始初始化前、类初始化后、类的某个方法调用前、类的某个方法调用后、方法抛出异常后等。
 *      Spring 只支持类的方法前、后、抛出异常后的连接点。
 *  4、切面（Aspect）：
 *      切面由切点和增强(或引介)组成，或者只由增强（或引介）实现。
 *      就是我们定义了标识@Aspect注解的类，这个类就是用来切面处理的
 *  5、目标对象（Target）：
 *      目标对象就是我们需要对它进行增强的业务类~，如果没有AOP，那么该业务类就得自己实现需要的功能。
 *  6、AOP代理（AOP Proxy）：
 *      一个类被AOP织入后生成出了一个结果类，它是融合了原类和增强逻辑的代理类。
 *  7、织入（Weaving）：
 *      织入就是将增强添加到目标类具体连接点上的过程。
 *      编译期织入，这要求使用特殊java编译器
 *      类装载期织入，这要求使用特殊的类装载器
 *      动态代理织入，在运行期为目标类添加增强生成子类的方式
 *      Spring采用的是动态代理织入，而AspectJ采用编译期织入和类装载期织入。
 *
 * @Before: 方法执行之前通知
 * @After: 方法执行之后通知，无论方法是否抛出异常，都会执行，由于方法可能抛出异常，所以可能得不到方法的返回值
 * @AfterReturning: 方法正常返回通知
 * @AfterThrowing: 方法异常通知
 * @Aroud: 环绕通知，环绕通知类似于动态代理的全过程，ProceedingJoinPoint为方法的参数，ProceedingJoinPoint可以决定是否执行目标方法
 * @PointCut: 切面切入点，抽取公共的切入点，其他方法直接引用被这个注解标注的方法，比如@Before("pointCut()")
 * @Aspect: 告诉spring 当前类是一个切面类。
 *
 *  Spring 中 JDK与Cglib：
 *      如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP
 *      如果目标对象实现了接口，可以强制使用CGLIB实现AOP
 *      如果目标对象没有实现接口，必须采用CGLIB库，Spring会自动在JDK动态代理和CGLIB动态代理之间切换
 * JDK动态代理：
 *      JDK的动态代理机制只能代理实现了接口的类，而不能实现接口的类就不能实现JDK的动态代理。
 * CGlib动态代理：
 *      cglib是针对类来实现代理的，他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。
 *      cglib包的底层是通过一个小而快的字节码处理框架ASM，来转换字节码并生成新的类。
 * 反射为什么效率低：
 *      很多方法都是通过Unsafe类调用的，是jni的，JVM无法做出优化。
 * ASM：
 *      ASM 能够通过改造既有类，直接生成需要的代码。增强的代码是硬编码在新生成的类文件内部的，没有反射带来性能上的付出。
 *
 */

@Order(1)   //通过order设置切面的优先级，越小优先级越高
@Aspect
@Component
public class LoggingAspect {

    /**
     * 定义这个方法，用于声明切入点表达式，一般地，该方法里面不需要任何代码
     */
    @Pointcut("execution(* com.yang.spring.service..*.*(..))")
    public void joinPointExpression(){}

//    @Before("execution(* com.yang.spring.service..*.*(..))")
    //com.yang.spring.aop.LoggingAspect.joinPointExpression() 这样写表示其他切面可以引用这个类的切入点方法
    @Before("com.yang.spring.aop.LoggingAspect.joinPointExpression()")
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = {Exception.class},readOnly = true,timeout = 3)
    public void before(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        List<Object> list= Arrays.asList(args);
        System.out.println("before action,args:"+list+",methodName:"+methodName);
    }

    /**
     * 环绕通知需要携带ProceedingJoinPoint 类型的参数
     * 环绕通知类似于动态代理的全过程：ProceedingJoinPoint 类型的参数可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值即为目标的返回值
     * @param proceedingJoinPoint
     * @return
     */
//    @Around("execution(* com.yang.spring.service..*.*(..))")
    @Around(value = "joinPointExpression()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        //环绕的意思是可以像动态代理一样灵活的在方法的各个切入点植入自己的逻辑
        System.out.println("around invoke...");
        Object result=null;
        String methodName=proceedingJoinPoint.getSignature().getName();
        try {
            //前置通知
            System.out.println("around 前置通知...");
            System.out.println("this abstracts name:"+methodName);
            //执行目标方法
            result=proceedingJoinPoint.proceed();
            //后置通知
            System.out.println("around 后置通知...");
        } catch (Throwable throwable) {
            //异常通知
            System.out.println("around 异常通知...");
            throwable.printStackTrace();
        }
        return result;
    }
}
