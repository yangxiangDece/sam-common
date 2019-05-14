package com.yang.spring.myspring;

/**
 * <p>Title:MyBeanDefinition</p>
 * <p>Description:Bean的定义</p>
 * <p>Company:成都瑞智创家科技有限公司</p>
 *
 * @author Yang Xiang
 * @date 2019/5/14 11:46
 */
public class MyBeanDefinition {

    /**
     * bean
     */
    private Object bean;

    /**
     * bean 的 class 对象
     */
    private Class beanClass;

    /**
     * bean 的类全限定名称
     */
    private String className;

    /**
     * 类的属性集合
     */
    private MyPropertyValues propertyValues = new MyPropertyValues();

    /**
     * 获取bean对象
     */
    public Object getBean() {
        return bean;
    }

    /**
     * 设置bean的对象
     */
    public void setBean(Object bean) {
        this.bean = bean;
    }

    /**
     * 获取bean的class对象
     * @return
     */
    public Class getBeanClass() {
        return beanClass;
    }

    /**
     * 通过设置类名称反射生成class对象
     */
    public void setClassName(String name) {
        this.className = name;
        try {
            this.beanClass = Class.forName(name);
        } catch (ClassNotFoundException e) {
            //抛出异常记录到日志
        }
    }

    public MyPropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(MyPropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
