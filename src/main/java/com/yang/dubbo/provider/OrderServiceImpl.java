package com.yang.dubbo.provider;

import com.yang.dubbo.api.entity.Order;
import com.yang.dubbo.api.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public Order getOrders(String orderId) {
        LOGGER.info("orderId:{}",orderId);
        return new Order(orderId,new BigDecimal(255.25));
    }
}
