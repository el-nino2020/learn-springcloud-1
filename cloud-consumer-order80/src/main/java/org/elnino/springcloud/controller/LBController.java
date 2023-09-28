package org.elnino.springcloud.controller;

import org.elnino.springcloud.entity.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class LBController {
    // ApplicationContextConfig 中定义的 RestTemplate 上有 @LoadBalanced 注解
    // 这里我们需要自己实现 LB，因此手动创建一个 RestTemplate
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private DiscoveryClient discoveryClient;

    // 记录控制器方法 customLB() 调用下游服务的总请求数，由于 web 服务器是多线程的，需要保证线程安全
    private final AtomicInteger counter = new AtomicInteger(0);

    // 自旋锁实现 counter 的 incrementAndGet
    private int incrementAndGet() {
        int cur, next;
        do {
            cur = counter.get();
            // next = cur + 1，同时防止整数溢出
            next = cur == Integer.MAX_VALUE ? 0 : cur + 1;
        } while (!counter.compareAndSet(cur, next));
        System.out.println("******** next: " + next);
        return next;
    }

    @GetMapping("/consumer/lb/rr")
    public CommonResult customLB() {
        // 获取服务注册列表
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        // 下游服务不可用
        if (instances == null || instances.size() == 0) {
            return new CommonResult(502, "server side error");
        }
        // 找到本次要访问的实例
        int index = incrementAndGet() % instances.size();
        ServiceInstance server = instances.get(index);
        System.out.println("********** " + server.getUri());
        // 访问实例
        return restTemplate.getForObject(server.getUri() + "/payment/get/1", CommonResult.class);
    }

}
