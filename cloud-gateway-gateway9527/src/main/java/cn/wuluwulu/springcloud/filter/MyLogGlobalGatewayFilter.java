package cn.wuluwulu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 网关全局过滤器-打印日志
 */
@Component
@Slf4j
public class MyLogGlobalGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*********** come in MyLogGlobalGatewayFilter: "+new Date());
        ServerHttpRequest request = exchange.getRequest();
        String uname = request.getQueryParams().getFirst("uname");
        if (uname==null){
            log.error("***********用户名为null,非法用户:",new RuntimeException("用户名为null,非法用户"));
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            // 不放行,直接返回response
            return response.setComplete();
        }

        return chain.filter(exchange);
    }

    /**
     * 过滤器的加载顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
