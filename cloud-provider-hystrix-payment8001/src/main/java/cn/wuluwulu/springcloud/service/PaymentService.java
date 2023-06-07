package cn.wuluwulu.springcloud.service;

import cn.wuluwulu.springcloud.entity.CommonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public CommonResult<String> paymentInfoOk(Integer id) {
        return new CommonResult<>(200, "线程池: " + Thread.currentThread().getName() + " payment info ok, id: " + id + "\t O(∩_∩)O");
    }

    /**
     * 调用该方法失败会自动调用HystrixCommand注解中的fallbackMethod中的方法
     * HystrixProperty 设置8001自身调用超时时间,峰值内可以正常运行,超过了需要有兜底方案
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfoErrorHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public CommonResult<String> paymentInfoError(Integer id) {
        // int a = 10 / 0;
        int timeout = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommonResult<>(200, "线程池: " + Thread.currentThread().getName() + ", id: " + id + "\t 耗时:" + timeout);
    }

    /**
     * 回退方法
     *
     * @param id
     * @return
     */
    public CommonResult<String> paymentInfoErrorHandler(Integer id) {
        return new CommonResult<>(200, "线程池: " + Thread.currentThread().getName() + "系统繁忙请稍后再试, id: " + id + "┭┮﹏┭┮");
    }
}
