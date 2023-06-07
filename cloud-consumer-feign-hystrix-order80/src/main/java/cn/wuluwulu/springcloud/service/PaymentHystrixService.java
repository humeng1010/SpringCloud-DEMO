package cn.wuluwulu.springcloud.service;

import cn.wuluwulu.springcloud.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    CommonResult<String> paymentInfoOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/fail/{id}")
    CommonResult<String> paymentInfoError(@PathVariable("id") Integer id);

}
