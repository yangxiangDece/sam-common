package com.yang.springannotation.config;

import com.yang.springannotation.aop.LogAspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 注解@EnableAspectJAutoProxy，表示开启注解的aop模式
 */
@EnableAspectJAutoProxy
@Configuration
@ComponentScan(value = "com.yang.springannotation.service")
public class SpringAopConfig {

    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
