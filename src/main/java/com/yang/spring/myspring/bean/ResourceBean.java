package com.yang.spring.myspring.bean;

public class ResourceBean {

    private String text;

    public void print() {
        System.out.println(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
