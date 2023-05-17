package cn.wuluwulu.springcloud.controller;

import cn.wuluwulu.springcloud.entity.CommonResult;
import cn.wuluwulu.springcloud.entity.Payment;
import cn.wuluwulu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    @Qualifier("paymentServiceImpl")
    private PaymentService paymentService;

    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        log.info("创建支付");
        return paymentService.create(payment);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("查询支付");
        log.info("ok");
        return paymentService.getPaymentById(id);
    }

}
