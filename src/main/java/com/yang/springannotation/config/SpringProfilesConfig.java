package com.yang.springannotation.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringValueResolver;

/**
 *
 * @Profile 指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件。
 *  如果启动容器的时候，没有激活@Profile配置，那么被标记了@Profile的bean都不会注册到容器中。
 *  @Profile 也可以放在类上面，表示只有激活了这个配置，这个配置类里面的bean才能被注册到容器中
 *
 *  配置为@Profile("default")，不需要激活，默认会注册这个标记的bean到容器中
 *
 *  激活方式：
 *      1、使用命令动态参数：在虚拟机参数位置加载 -Dspring.profiles.active=test
 *      2、使用applicationContext.getEnvironment().setActiveProfiles("test","dev");
 *
 */

//@Profile("test")
@PropertySource(value = {"classpath:jdbc.properties"})
@Configuration
public class SpringProfilesConfig implements EmbeddedValueResolverAware {

    @Value("${jdbc.driverClass}")
    private String driverClass;

    private StringValueResolver resolver;

    @Profile("test")
    @Bean("dataSourceTest")
    public DruidDataSource dataSourceTest(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        String driverClass = this.resolver.resolveStringValue("${jdbc.driverClass}");
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setMaxActive(6000);
        return druidDataSource;
    }

    @Profile("dev")
    @Bean("dataSourceDev")
    public DruidDataSource dataSourceDev(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/dev?useUnicode=true&characterEncoding=utf8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setMaxActive(6000);
        return druidDataSource;
    }

    @Profile("prod")
    @Bean("dataSourceProd")
    public DruidDataSource dataSourceProd(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/prod?useUnicode=true&characterEncoding=utf8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setMaxActive(6000);
        return druidDataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver=resolver;
    }
}
