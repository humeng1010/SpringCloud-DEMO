package cn.wuluwulu.springcloud.controller;

import cn.wuluwulu.springcloud.entity.CommonResult;
import cn.wuluwulu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/consumer/payment")
@DefaultProperties(defaultFallback = "paymentGlobalHandler")// 默认
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/hystrix/ok/{id}")
    public CommonResult<String> paymentInfoOk(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoOk(id);
    }

    @GetMapping("/hystrix/fail/{id}")
    // @HystrixCommand(fallbackMethod = "paymentInfoErrorHandler", commandProperties = {
    //         @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    // })
    @HystrixCommand
    public CommonResult<String> paymentInfoError(@PathVariable("id") Integer id) {
        int a = 10 / 0;
        return paymentHystrixService.paymentInfoError(id);
    }

    public CommonResult<String> paymentInfoErrorHandler() {
        return new CommonResult<>(501, "这是80消费端,对方支付系统繁忙请10秒钟后再试,或者自己运行出错请检查自己┭┮﹏┭┮");
    }

    public CommonResult<String> paymentGlobalHandler() {
        return new CommonResult<>(500, "80消费端Global异常┭┮﹏┭┮");
    }
}
