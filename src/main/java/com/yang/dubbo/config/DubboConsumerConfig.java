package com.yang.dubbo.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.yang.dubbo.api.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * dubbo 服务消费方配置
 */
@DubboComponentScan(basePackages = "com.yang.dubbo.api.service")
@Configuration
public class DubboConsumerConfig {

    //消费者可以直接连服务提供者
//    @Reference(url = "127.0.0.1:20881")
    @Reference
    private UserService userService;

    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig=new ApplicationConfig();
        applicationConfig.setName("consumer-annotation-test");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig=new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("192.168.75.128:2181");
        registryConfig.setClient("curator");
        return registryConfig;
    }

    @Bean
    public UserService userService(){
        return userService;
    }
}
