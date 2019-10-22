package com.yang.springannotation;

import com.alibaba.druid.pool.DruidDataSource;
import com.yang.springannotation.config.SpringProfilesConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class SpringProfilesTest {

    public static void main(String[] args) {
        //1、创建一个applicationContext对象，调用无参构造方法，这时候容器并没有启动
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //2、设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test", "dev");
        //3、注册主配置的类
        applicationContext.register(SpringProfilesConfig.class);
        //4、启动刷新容器，启动容器
        applicationContext.refresh();

        //只获取DruidDataSource类型的bean
        String[] beanNamesForType = applicationContext.getBeanNamesForType(DruidDataSource.class);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println(environment);
        //获取所有的bean
//        applicationContext.getBeanDefinitionNames();
    }
}
