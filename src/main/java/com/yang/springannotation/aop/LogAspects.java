package com.yang.springannotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspects {

    @Pointcut("execution(* com.yang.springannotation.service..*.*(..))")
    public void pointCut() {
    }

    @Before(value = "com.yang.springannotation.aop.LogAspects.pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("@Before：" + joinPoint.getSignature().getName() + "运行，参数列表：" + Arrays.asList(joinPoint.getArgs()));
    }

    @After(value = "pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("@After：" + joinPoint.getSignature().getName() + "运行，参数列表：" + Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("@AfterReturning：" + joinPoint.getSignature().getName() + "正常返回，运行结果：" + result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println("@AfterThrowing：" + joinPoint.getSignature().getName() + " 产生异常，异常信息：" + exception.getMessage());
    }
}
