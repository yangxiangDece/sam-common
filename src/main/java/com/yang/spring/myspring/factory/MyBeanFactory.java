package com.yang.spring.myspring.factory;

import com.yang.spring.myspring.MyBeanDefinition;

/**
 * <p>Title:</p>
 * <p>Description:需要一个beanFactory 定义ioc容器的一些行为 比如根据名称获取bean， 参数为bean的名称， bean的定义</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 *
 * @author Yang Xiang
 * @date 2019/5/14 11:43
 */
public interface MyBeanFactory {

    /**
     * 根据bean的名称从容器中获取bean对象
     *
     * @param name
     * @return
     * @throws Exception
     */
    Object getBean(String name) throws Exception;

    /**
     * 将bean注册到容器中
     *
     * @param name
     * @param bea
     * @throws Exception
     */
    void registerBeanDefinition(String name, MyBeanDefinition bea) throws Exception;
}
