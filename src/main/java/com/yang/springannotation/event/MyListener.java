package com.yang.springannotation.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent event) {
        String msg=event.getMsg();
        System.out.println("收到来自MyEvent事件发布的消息：" + msg);
    }
}
