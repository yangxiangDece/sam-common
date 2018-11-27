package com.yang.springannotation.config;

import org.springframework.context.annotation.Configuration;

/**
 * BeanPostProcessor：bean的后置处理器，bean创建对象初始化前后进行拦截工作的
 * BeanFactoryPostProcessor：beanFactory的后置处理器，
 *      在beanFactory标准初始化以后调用，这时候所有的bean定义已经保存到beanFactory中，但是bean的实例还未创建
 * BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor：
 *      在所有bean定义信息将要被加载时调用，这是bean实例还未创建，它是比BeanFactoryPostProcessor更早执行
 *
 *
 */
@Configuration
public class ExtConfig {
}
