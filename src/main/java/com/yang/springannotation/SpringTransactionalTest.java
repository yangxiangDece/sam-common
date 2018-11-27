package com.yang.springannotation;

import com.yang.springannotation.config.SpringTransactionalConfig;
import com.yang.springannotation.service.MathCalculatorService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTransactionalTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringTransactionalConfig.class);
        MathCalculatorService mathCalculatorService = (MathCalculatorService) applicationContext.getBean("mathCalculatorService2");
        double div = mathCalculatorService.div(12, 6);
        System.out.println("执行结果："+div);
    }
}
