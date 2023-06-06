package cn.wuluwulu.springcloud.controller;

import cn.wuluwulu.springcloud.entity.CommonResult;
import cn.wuluwulu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Autowired
    private HttpServletRequest request;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/ok/{id}")
    public CommonResult<String> paymentInfoOk(@PathVariable("id") Integer id) {
        CommonResult<String> result = paymentService.paymentInfoOk(id);
        log.info("result:{},port:{}", result, serverPort);
        return result;
    }

    @GetMapping("/hystrix/fail/{id}")
    public CommonResult<String> paymentInfoError(@PathVariable Integer id) {
        CommonResult<String> result = paymentService.paymentInfoError(id);
        log.info("result:{},port:{}", result, serverPort);
        return result;
    }


}
