package com.yang.springannotation.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @EnableAspectJAutoProxy 注解表示开启AOP注解的功能，是在使用AspectJ AOP 功能的时候使用
 * @EnableTransactionManagement 表示开启注解事务，当使用@Transactional注解时需要开启它，并且需要配置事务管理数据源
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(value = "com.yang.springannotation.service")
public class SpringTransactionalConfig {

    @Bean("dataSource")
    public DruidDataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setMaxActive(5000);
        return druidDataSource;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
