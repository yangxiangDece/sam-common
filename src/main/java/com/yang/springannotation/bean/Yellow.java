package com.yang.springannotation.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Yellow {

    @Value("${jdbc.url}")
    private String lastName;
    private String name;

    public Yellow() {
        System.out.println("Yellow constructor ...");
    }

    /**
     * 对象创建并赋值完成调用
     */
    @PostConstruct
    public void init(){
        System.out.println("Yellow... @PostConstruct...");
    }

    /**
     * 容器移除对象之前调用
     */
    @PreDestroy
    public void destroy(){
        System.out.println("Yellow... @PreDestroy ...");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Yellow{" +
                "lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
