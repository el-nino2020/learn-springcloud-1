package org.elnino.springcloud.service;

import org.elnino.springcloud.entity.CommonResult;
import org.elnino.springcloud.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(446, "服务降级返回,没有该流水信息", new Payment(id, "errorSerial......"));
    }
}
