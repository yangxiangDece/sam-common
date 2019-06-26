package com.yang.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class MyEmailNotifier implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof MyEmailEvent) {
            MyEmailEvent emailEvent = (MyEmailEvent) event;
            System.out.println("收到MyEmailEvent发布的事件...");
            System.out.println(emailEvent);
        } else if (event instanceof ContextRefreshedEvent) {
            System.out.println("监听到所有的Bean被成功装载，后处理Bean被检测并激活，所有Singleton Bean 被预实例化，ApplicationContext容器已就绪可用！");
        } else {
            System.out.println("容器自身的事件..." + event);
        }
    }
}
