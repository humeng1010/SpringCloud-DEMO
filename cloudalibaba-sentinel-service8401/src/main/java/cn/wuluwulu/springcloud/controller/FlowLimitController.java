package cn.wuluwulu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    @SentinelResource(value = "testA", blockHandler = "testABlockHandler", fallback = "testAFallback")
    public String testA() {
        return "----testA";
    }

    public String testAFallback() {
        return "testA fallback is call ";
    }

    public String testABlockHandler(BlockException ex) {
        ex.printStackTrace();
        return "testA block handler is call";
    }

    @GetMapping("/testB")
    public String testB() {
        return "----testB";
    }
}
