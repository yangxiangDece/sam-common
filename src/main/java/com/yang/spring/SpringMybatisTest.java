package com.yang.spring;

import com.yang.spring.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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

    public static void testMybatis() throws Exception{
        String resource="mybatis-config.xml";
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = new User("张三", "123456");
            userDao.insert(user);
            sqlSession.commit();
        }
    }
}
