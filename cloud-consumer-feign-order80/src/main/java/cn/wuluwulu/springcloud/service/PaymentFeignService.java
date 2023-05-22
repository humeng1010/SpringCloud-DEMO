package cn.wuluwulu.springcloud.service;

import cn.wuluwulu.springcloud.entity.CommonResult;
import cn.wuluwulu.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 使用feign进行 远程程序调用 RPC: remote process call
 */
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") // 声明需要调用Eureka中的哪个服务
public interface PaymentFeignService {

    @GetMapping("/payment/create")
    CommonResult<Integer> create(Payment payment);

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
