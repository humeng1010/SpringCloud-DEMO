package cn.wuluwulu.springcloud.service;

import cn.wuluwulu.springcloud.entity.CommonResult;
import cn.wuluwulu.springcloud.entity.Payment;

public interface PaymentService {
    CommonResult<Integer> create(Payment payment);

    CommonResult<Payment> getPaymentById(Long id);
}
