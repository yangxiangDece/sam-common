package com.yang.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        super();
        System.out.println("BeanFactoryPostProcessor 构造器 invoke... ");
    }

    /**
     * 如果通过beanFactory.getBean(beanName)，那么会提前触发bean的实例化，那么BeanPostProcessor接口将不会执行
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("[BeanFactoryPostProcessor] postProcessBeanFactory invoke...");
        BeanDefinition bd = beanFactory.getBeanDefinition("student");
        bd.getPropertyValues().addPropertyValue("phone", "12456");
    }
}
