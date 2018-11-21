package com.yang.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyApplicationContext implements ApplicationContextAware {

    public MyApplicationContext() {
        super();
        System.out.println("[ApplicationContextAware] 构造器 invoke...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("[ApplicationContextAware] setApplicationContext invoke ... ");
    }
}
