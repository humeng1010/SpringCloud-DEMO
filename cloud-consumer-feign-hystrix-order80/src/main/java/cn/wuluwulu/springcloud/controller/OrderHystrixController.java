package cn.wuluwulu.springcloud.controller;

import cn.wuluwulu.springcloud.entity.CommonResult;
import cn.wuluwulu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/hystrix/ok/{id}")
    public CommonResult<String> paymentInfoOk(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoOk(id);
    }

    @GetMapping("/hystrix/fail/{id}")
    public CommonResult<String> paymentInfoError(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoError(id);
    }

}
