package com.yang.spring.processor;

import com.yang.spring.bean.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * 可以通过FactoryBean自定义bean工厂，创建自己的bean实例
 */
public class MyFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        //由于getObject()方法每次放回的都是一个新的User，所以不是单例的，是多例的
        return false;
    }
}
