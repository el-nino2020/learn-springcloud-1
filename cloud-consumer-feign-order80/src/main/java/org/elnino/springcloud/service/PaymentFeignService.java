package org.elnino.springcloud.service;

import org.elnino.springcloud.entity.CommonResult;
import org.elnino.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") // 服务提供者
public interface PaymentFeignService {
    // 直接拷贝服务提供者的控制器方法的签名，这里是
    // org.elnino.springcloud.controller.PaymentController#getPaymentById
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeOut();
}
