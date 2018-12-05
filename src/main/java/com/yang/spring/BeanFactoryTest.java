package com.yang.spring;

import com.yang.spring.service.UserService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BeanFactoryTest {

    public static void main(String[] args) {
        //XmlBeanFactory 已被废弃，可以使用下面这种方式
        Resource resource = new ClassPathResource("spring-context.xml");
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);

        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService);
    }
}
