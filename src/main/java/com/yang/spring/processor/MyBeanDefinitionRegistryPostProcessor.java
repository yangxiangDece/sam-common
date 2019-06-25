package com.yang.spring.processor;

import com.yang.springannotation.bean.Blue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * BeanPostProcessor：bean的后置处理器，bean创建对象初始化前后进行拦截工作的
 * <p>
 * BeanFactoryPostProcessor：beanFactory的后置处理器，在beanFactory标准初始化以后调用，这时候所有的bean定义已经保存到beanFactory中，但是bean的实例还未创建
 * <p>
 * BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor：在所有bean定义信息将要被加载时调用，这时bean实例还未创建，它是比BeanFactoryPostProcessor更早执行
 */
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    /**
     * postProcessBeanDefinitionRegistry先执行，这里向容器中注册了beanDefinition，postProcessBeanFactory后执行，
     * postProcessBeanFactory比postProcessBeanDefinitionRegistry多打印一个bean的数量
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("[MyBeanDefinitionRegistryPostProcessor] postProcessBeanDefinitionRegistry invoke...bean的数量：" + registry.getBeanDefinitionCount());
//        BeanDefinition beanDefinition=new RootBeanDefinition(Blue.class);
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Blue.class).getBeanDefinition();
        registry.registerBeanDefinition("BeanDefinitionRegistryPostProcessor--Blue", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("[MyBeanDefinitionRegistryPostProcessor] postProcessBeanFactory invoke...bean的数量：" + beanFactory.getBeanDefinitionCount());
    }
}
