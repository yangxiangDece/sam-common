package com.yang.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yang.dubbo.api.entity.User;
import com.yang.dubbo.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User findById(String id) {
        LOGGER.info("UserServiceImpl...invoke,args:" + id);
        return new User(id, "张三", "1232324234");
    }
}
