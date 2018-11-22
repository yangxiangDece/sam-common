package com.yang.spring;

import com.yang.spring.bean.User;
import com.yang.spring.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMybatisTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-mybatis.xml");
        UserDao userDao = applicationContext.getBean(UserDao.class);

        User user=new User("张三","123456");
        userDao.insert(user);

        System.out.println(userDao.getUsers());
    }
}
