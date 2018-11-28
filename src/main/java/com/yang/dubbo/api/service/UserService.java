package com.yang.dubbo.api.service;

import com.yang.dubbo.api.entity.User;

public interface UserService {

    User findById(String id);
}
