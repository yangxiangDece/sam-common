package com.yang.spring;

import com.yang.spring.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * BeanFactory：采用延迟加载，第一次getBean时才会初始化Bean
 * ApplicationContext：容器启动初始化上下文就会实例化所有单例的bean，是对BeanFactory扩展，提供了更多的功能
 *      1、MessageSource, 提供国际化的消息访问
        2、资源访问，如URL和文件
        3、事件传播特性，即支持aop特性
        4、载入多个（有继承关系）上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的web层
 * ClassPathXmlApplicationContext：从类路径下加载配置文件
 * FileSystemXmlApplicationContext：从文件系统中加载配置文件
 * ConfigurableApplicationContext扩展于ApplicationContext，新增加了两个主要方法：refresh()和close()，让ApplicationContext具有启动、刷新和关闭上下文的功能。
 *
 *
 */
public class SpringTest {

    public static void main(String[] args) throws Exception {

//        UserService userService=new UserServiceImpl();
//        UserService userServiceProxy = (UserService) new MyProxy().getInstance(userService);
//        userServiceProxy.add(1, 2);

//        Resource resource = new ClassPathResource("spring-context.xml");
//        BeanFactory beanFactory=new XmlBeanFactory(resource);

        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-context.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        int add = userService.add(12, 112);
        System.out.println(add);

    }
}
