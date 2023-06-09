package cn.wuluwulu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    /**
     * 配置类一个路由id为route1的路由
     * 当访问 http://localhost:9527/l 时候,会自动跳到 https://huya.com/l
     * 当访问 http://localhost:9527/g 时候,会自动跳到 https://huya.com/g
     *
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("route1",
                        r -> r.path("/l").uri("https://huya.com/"))
                .route("route2",
                        r -> r.path("/g").uri("https://huya.com/"))
                .build();
    }
}
