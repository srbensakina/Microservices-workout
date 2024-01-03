package com.example.apigateway.config;


import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
//@EnableHystrix
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("workout-service", r -> r.path("/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://workout-service"))

                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))

                .route("chat-service", r -> r.path("/chat/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://chat-service"))
                .build();
    }

}