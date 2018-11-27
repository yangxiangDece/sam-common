package com.yang.springannotation;

import com.yang.springannotation.config.SpringAopConfig;
import com.yang.springannotation.service.MathCalculatorService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAopTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringAopConfig.class);
        MathCalculatorService mathCalculatorService = (MathCalculatorService) applicationContext.getBean("mathCalculatorService");
        double div = mathCalculatorService.div(12, 6);
        System.out.println("执行结果："+div);
    }
}
