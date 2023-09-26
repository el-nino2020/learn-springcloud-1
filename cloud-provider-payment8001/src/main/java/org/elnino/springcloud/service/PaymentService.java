package org.elnino.springcloud.service;

import org.apache.ibatis.annotations.Param;
import org.elnino.springcloud.entity.Payment;

public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}