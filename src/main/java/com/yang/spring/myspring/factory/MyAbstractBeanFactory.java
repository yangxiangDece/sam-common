package com.yang.spring.myspring.factory;

import com.yang.spring.myspring.MyBeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title:MyAbstractBeanFactory</p>
 * <p>Description:一个抽象类， 实现了 bean 的方法，包含一个map，用于存储bean 的名字和bean的定义</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 *
 * @author Yang Xiang
 * @date 2019/5/14 13:51
 */
public abstract class MyAbstractBeanFactory implements MyBeanFactory {

    /**
     * 容器
     */
    private Map<String, MyBeanDefinition> map = new HashMap<>();

    /**
     * 根据bean的名称获取bean， 如果没有，则抛出异常 如果有， 则从bean定义对象获取bean实例
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String name) throws Exception {
        MyBeanDefinition beanDefinition = map.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = doCreate(beanDefinition);
        }
        return bean;
    }

    /**
     * 注册 bean定义 的抽象方法实现，这是一个模板方法， 调用子类方法doCreate，
     *
     * @param name
     * @param beanDefinition
     * @throws Exception
     */
    @Override
    public void registerBeanDefinition(String name, MyBeanDefinition beanDefinition) throws Exception {
        Object bean = doCreate(beanDefinition);
        beanDefinition.setBean(bean);
        map.put(name, beanDefinition);
    }

    /**
     * 创建一个bean
     *
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    abstract Object doCreate(MyBeanDefinition beanDefinition) throws Exception;
}
