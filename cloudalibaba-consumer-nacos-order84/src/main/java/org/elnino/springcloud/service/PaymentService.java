package org.elnino.springcloud.service;

import org.elnino.springcloud.entity.CommonResult;
import org.elnino.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)//调用中关闭9003服务提供者
public interface PaymentService {
    @GetMapping(value = "/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
