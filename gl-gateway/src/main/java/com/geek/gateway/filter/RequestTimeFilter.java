package com.geek.gateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description: 记录接口请求时间,全局过滤器，不需要配置到规则中
 * @Author: 屈艳锋
 * @Date: 2019/8/14 15:04
 */
@Component
public class RequestTimeFilter implements GlobalFilter, Ordered {
    private static final Log log = LogFactory.getLog(GatewayFilter.class);
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //pre
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());

        return chain.filter(exchange).then(
                //post
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                    if (startTime != null) {
                        log.info("请求路径："+exchange.getRequest().getURI().getRawPath() + "消耗时间: " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                })
        );
    }
    @Override
    public int getOrder() {
        return 0;
    }
}

