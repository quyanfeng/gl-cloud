package com.geek.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description:token 拦截器,局部过滤器，需配置到规则中
 * @Author: 屈艳锋
 * @Date: 2019/8/14 15:12
 */
@Component
public class TokenFilter implements GatewayFilter, Ordered {
    Logger logger= LoggerFactory.getLogger( TokenFilter.class );
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        String uri=exchange.getRequest().getURI().getRawPath();
        if (token == null || token.isEmpty()) {
            logger.info( "token 为空，无法进行访问." );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
