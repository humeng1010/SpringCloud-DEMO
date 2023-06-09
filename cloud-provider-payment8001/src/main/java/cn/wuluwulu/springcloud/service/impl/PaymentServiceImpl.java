package cn.wuluwulu.springcloud.service.impl;

import cn.wuluwulu.springcloud.entity.CommonResult;
import cn.wuluwulu.springcloud.entity.Payment;
import cn.wuluwulu.springcloud.mapper.PaymentMapper;
import cn.wuluwulu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    // @Autowired // spring 提供的
    @Resource // java自带的
    private PaymentMapper paymentMapper;


    @Value("${server.port}")
    private String serverPort;

    // 构造器注入
    /*
    public PaymentServiceImpl(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }
    */

    @Override
    public CommonResult<Integer> create(Payment payment) {
        try {
            int data = paymentMapper.create(payment);
            return new CommonResult<>(200, "创建成功,serverPort:" + serverPort, data);
        } catch (Exception e) {
            return new CommonResult<>(508, "创建失败,serverPort:" + serverPort);
        }
    }

    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        Payment payment = paymentMapper.getPaymentById(id);
        if (payment == null) {
            return new CommonResult<>(508, "支付不存在,serverPort:" + serverPort);
        }
        return new CommonResult<>(200, "成功,serverPort:" + serverPort, payment);
    }
}
