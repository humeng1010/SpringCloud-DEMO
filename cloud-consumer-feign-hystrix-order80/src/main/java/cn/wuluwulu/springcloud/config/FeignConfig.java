package cn.wuluwulu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    // @Bean
    Logger.Level feignLoggerLevel() {
        // 详细日志
        return Logger.Level.FULL;
    }
}
