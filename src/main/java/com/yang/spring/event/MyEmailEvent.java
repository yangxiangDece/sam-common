package com.yang.spring.event;

import org.springframework.context.ApplicationEvent;

public class MyEmailEvent extends ApplicationEvent {

    private String address;
    private String text;

    public MyEmailEvent(Object source, String address, String text) {
        super(source);
        this.address = address;
        this.text = text;
    }

    public MyEmailEvent(Object source) {
        super(source);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MyEmailEvent{" +
                "address='" + address + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
