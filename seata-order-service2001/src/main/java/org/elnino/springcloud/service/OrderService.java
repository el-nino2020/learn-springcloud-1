package org.elnino.springcloud.service;

import org.elnino.springcloud.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}