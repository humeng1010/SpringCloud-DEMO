package cn.wuluwulu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import cn.wuluwulu.springcloud.entity.CommonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public CommonResult<String> paymentInfoOk(Integer id) {
        return new CommonResult<>(200,
                "线程池: " + Thread.currentThread().getName() + " payment info ok, id: " + id + "\t O(∩_∩)O");
    }


    // ====服务降级

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
        return new CommonResult<>(200,
                "线程池: " + Thread.currentThread().getName() + ", id: " + id + "\t 耗时:" + timeout);
    }

    /**
     * 回退方法
     * 服务降级
     *
     * @param id
     * @return
     */
    public CommonResult<String> paymentInfoErrorHandler(Integer id) {
        return new CommonResult<>(200,
                "线程池: " + Thread.currentThread().getName() + "系统繁忙请稍后再试, id: " + id + "┭┮﹏┭┮");
    }

    // ======服务熔断

    /**
     * 下面注解配置的为:
     * 10秒钟的时间窗口内,只有请求次数大于10并且出错的错误率大于60%才会触发断路器
     * 当开启断路器所有请求都不会进行转发
     * 一段时间后(默认5秒,这里设置10秒),这个时候断路器是半开状态,会让其中一个请求进行转发.
     * 如果成功断路器会关闭,如果失败,继续开启断路器.
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 断路睡眠时间窗口期10秒
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
    public CommonResult<String> paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return new CommonResult<>(202, "调用成功,流水号: " + serialNumber);
    }

    public CommonResult<String> paymentCircuitBreakerFallback(Integer id) {
        return new CommonResult<>(501, "id: " + id + " 不能为负数┭┮﹏┭┮");

    }

}
