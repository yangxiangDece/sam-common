package com.yang.spring.myspring;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * <p>Title:</p>
 * <p>Description:需要一个beanFactory 定义ioc容器的一些行为 比如根据名称获取bean， 参数为bean的名称， bean的定义</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 * @author Yang Xiang
 * @date 2019/5/14 11:43
 */
public interface MyBeanFactory {

    Object getBean(String name) throws Exception;

    void registerBeanDefinition(String name, BeanDefinition bea) throws Exception;
}
