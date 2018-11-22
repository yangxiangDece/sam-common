package com.yang.springannotation.bean;

import org.springframework.beans.factory.annotation.Value;

public class PropertiesBean {

    @Value("${jdbc.url}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PropertiesBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
