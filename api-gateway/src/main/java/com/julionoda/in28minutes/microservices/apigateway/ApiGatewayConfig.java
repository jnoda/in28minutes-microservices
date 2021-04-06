package com.julionoda.in28minutes.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                // demo route
                .route(p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))

                // currency exchange
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange-service"))

                // currency conversion
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion-service"))

                // currency conversion (rewrite demo)
                .route(p -> p.path("/currency-conversion-rewrite/**")
                        .filters(f -> f.rewritePath("/currency-conversion-rewrite/", "/currency-conversion/"))
                        .uri("lb://currency-conversion-service"))
                .build();
    }
}
