package cn.wuluwulu.springcloud.controller;

import cn.wuluwulu.springcloud.entity.CommonResult;
import cn.wuluwulu.springcloud.entity.Payment;
import cn.wuluwulu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/create")
    CommonResult<Integer> create(Payment payment) {
        return paymentFeignService.create(payment);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/test/timeout")
    public String testTimeOut() {
        // open feign - ribbon ,客户端一般默认等待1秒钟
        
        return paymentFeignService.testTimeOut();
    }

}
