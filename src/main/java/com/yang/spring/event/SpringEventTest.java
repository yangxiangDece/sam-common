package com.yang.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringEventTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringEventConfiguration.class);

        MyEmailEvent emailEvent = new MyEmailEvent("hello", "武侯区", "这是哪个？");
        applicationContext.publishEvent(emailEvent);
    }
}
