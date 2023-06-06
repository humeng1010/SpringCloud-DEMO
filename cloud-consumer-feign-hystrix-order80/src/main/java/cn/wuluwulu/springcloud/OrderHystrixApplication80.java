package cn.wuluwulu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderHystrixApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixApplication80.class, args);
    }
}
