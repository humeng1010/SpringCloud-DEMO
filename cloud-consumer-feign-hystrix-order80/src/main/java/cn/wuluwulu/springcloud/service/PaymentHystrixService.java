package cn.wuluwulu.springcloud.service;

import cn.wuluwulu.springcloud.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
@RequestMapping("/payment")
public interface PaymentHystrixService {
    @GetMapping("/hystrix/ok/{id}")
    CommonResult<String> paymentInfoOk(@PathVariable("id") Integer id);

    @GetMapping("/hystrix/fail/{id}")
    CommonResult<String> paymentInfoError(@PathVariable("id") Integer id);

}
