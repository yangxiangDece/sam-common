package com.yang.dubbo.api.entity;

import java.math.BigDecimal;

public class Order {

    private String orderId;

    private BigDecimal price;

    public Order(String orderId, BigDecimal price) {
        this.orderId=orderId;
        this.price=price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
