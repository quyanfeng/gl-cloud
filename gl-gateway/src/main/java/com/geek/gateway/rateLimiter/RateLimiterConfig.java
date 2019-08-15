package com.geek.gateway.rateLimiter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Description: 路由限流配置
 * @Author: 屈艳锋
 * @Date: 2019/8/15 15:47
 */
@Configuration
public class RateLimiterConfig {

    @Bean(value = "remoteAddrKeyResolver")
    public KeyResolver remoteAddrKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
