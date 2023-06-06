package cn.wuluwulu.springcloud.service;

import cn.wuluwulu.springcloud.entity.CommonResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public CommonResult<String> paymentInfoOk(Integer id) {
        return new CommonResult<>(200, "线程池: " + Thread.currentThread().getName() + " payment info ok, id: " + id + "\t O(∩_∩)O");
    }

    public CommonResult<String> paymentInfoError(Integer id) {
        int timeout = 3;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommonResult<>(200, "线程池: " + Thread.currentThread().getName() + " payment info timeout, id: " + id + "\t 耗时:" + timeout);

    }
}
