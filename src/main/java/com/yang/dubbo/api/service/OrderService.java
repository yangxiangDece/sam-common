package com.yang.dubbo.api.service;

import com.yang.dubbo.api.entity.Order;

public interface OrderService {

    Order getOrders(String orderId);
}
