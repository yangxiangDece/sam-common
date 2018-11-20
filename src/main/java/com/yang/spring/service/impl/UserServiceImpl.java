package com.yang.spring.service.impl;

import com.yang.spring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public int add(int a, int b) {
        LOGGER.info("add invoke...");
        return a+b;
    }

    @Override
    public int subtract(int a, int b) {
        LOGGER.info("subtract invoke...");
        return a-b;
    }

    @Override
    public int multiply(int a, int b) {
        LOGGER.info("multiply invoke...");
        return a*b;
    }

    @Override
    public int divide(int a, int b) {
        LOGGER.info("divide invoke...");
        return a/b;
    }
}
