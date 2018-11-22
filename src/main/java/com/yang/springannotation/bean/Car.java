package com.yang.springannotation.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Car implements InitializingBean,DisposableBean {

    private String name;

    public Car(){
        System.out.println("car constructor...");
    }

    public void initMethod(){
        System.out.println("initMethod car invoke...");
    }

    public void destroyMethod(){
        System.out.println("destroyMethod car invoke...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[InitializingBean] car afterPropertiesSet invoke...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("[DisposableBean] car destroy invoke...");
    }
}
