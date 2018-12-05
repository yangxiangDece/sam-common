package com.yang.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * InstantiationAwareBeanPostProcessorAdapter 是 BeanPostProcessor 接口的实现类
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out.println("InstantiationAwareBeanPostProcessorAdapter 构造器 invoke ...");
    }

    /**
     * 接口方法、实例化Bean之前调用
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("[InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessBeforeInstantiation()...");
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    /**
     * 接口方法、实例化Bean之后调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("[InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessAfterInstantiation()...");
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    /**
     * 接口方法、设置某个属性时调用，设置每一个属性都会调用
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("[InstantiationAwareBeanPostProcessorAdapter] 调用了 postProcessProperties()...");
        return super.postProcessProperties(pvs, bean, beanName);
    }
}
