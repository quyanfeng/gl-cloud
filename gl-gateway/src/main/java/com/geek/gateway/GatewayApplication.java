package com.geek.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(GatewayApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //定义路由规则
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route",
                         r ->   r.path("/geekUser/**")
                                 .filters(f->f.stripPrefix(1))
                                 .uri("lb://GEEK-USER"))
                .build();
    }

}
