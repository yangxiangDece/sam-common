package com.yang.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        super();
        System.out.println("BeanPostProcessor 构造器 invoke...");
    }

    /**
     * 是在bean实例化，依赖注入之后及自定义初始化方法(例如：
     * 配置文件中bean标签添加init-method属性指定Java类中初始化方法、@PostConstruct注解指定初始化方法，Java类实现InitailztingBean接口)之前调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor] postProcessBeforeInitialization bean:"+beanName);
        return bean;
    }

    /**
     * 是在bean实例化、依赖注入及自定义初始化方法之后调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanPostProcessor] postProcessAfterInitialization bean:"+beanName);
        return bean;
    }
}
