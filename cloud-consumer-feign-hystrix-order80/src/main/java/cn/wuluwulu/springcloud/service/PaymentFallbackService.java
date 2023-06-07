package cn.wuluwulu.springcloud.service;

import cn.wuluwulu.springcloud.entity.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public CommonResult<String> paymentInfoOk(Integer id) {
        return new CommonResult<>(500, "payment fallback service ok; o(T_T)o");
    }

    @Override
    public CommonResult<String> paymentInfoError(Integer id) {
        return new CommonResult<>(500, "payment fallback service error; o(T_T)o");
    }
}
