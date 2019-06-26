package com.yang.spring.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringEventConfiguration {

    @Bean
    public MyEmailNotifier emailNotifier() {
        return new MyEmailNotifier();
    }
}
