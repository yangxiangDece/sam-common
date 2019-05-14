package com.yang.spring.myspring.factory;

import com.yang.spring.myspring.MyBeanDefinition;
import com.yang.spring.myspring.MyBeanReference;
import com.yang.spring.myspring.MyPropertyValue;

import java.lang.reflect.Field;

/**
 * <p>Title:MyAutowireBeanFactory</p>
 * <p>Description:实现自动注入和递归注入(spring 的标准实现类 DefaultListableBeanFactory 有 1810 行)</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 * @author Yang Xiang
 * @date 2019/5/14 13:56
 */
public class MyAutowireBeanFactory extends MyAbstractBeanFactory {

    /**
     * 根据bean 定义创建实例， 并将实例作为key， bean定义作为value存放，并调用 addPropertyValue 方法 为给定的bean的属性进行注入
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    @Override
    Object doCreate(MyBeanDefinition beanDefinition) throws Exception {
        Object bean =beanDefinition.getBeanClass().newInstance();
        // 填充bean的属性
        addPropertyValue(bean, beanDefinition);
        return bean;
    }

    /**
     * 给定一个bean定义和一个bean实例，为给定的bean中的属性注入实例。
     * @param bean
     * @param beanDefinition
     */
    private void addPropertyValue(Object bean, MyBeanDefinition beanDefinition) throws Exception {
        for (MyPropertyValue pv : beanDefinition.getPropertyValues().getPropertyValueList()) {
            // 根据给定属性名称获取 给定的bean中的属性对象
            Field declaredField = bean.getClass().getDeclaredField(pv.getName());
            // 设置属性的访问权限
            declaredField.setAccessible(true);
            // 获取定义的属性中的对象
            Object value = pv.getValue();
            // 判断这个对象是否是 BeanReference 对象
            if (value instanceof MyBeanReference) {
                MyBeanReference beanReference = (MyBeanReference) value;
                // 调用父类的 AbstractBeanFactory 的 getBean 方法，根据bean引用的名称获取实例，此处即是递归
                value = getBean(beanReference.getName());
            }
            // 反射注入bean的属性
            declaredField.set(bean, value);
        }
    }
}
