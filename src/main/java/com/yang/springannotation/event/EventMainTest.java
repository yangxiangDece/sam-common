package com.yang.springannotation.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class EventMainTest {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(EventConfig.class);
        MyEventPublisher publisher = applicationContext.getBean(MyEventPublisher.class);
        publisher.publish("hello my application event...");
    }
}
