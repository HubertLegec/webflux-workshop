package com.legec.webfluxworkshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Instant;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
class FunctionalEndpoints {
    @Bean
    RouterFunction<ServerResponse> getDate() {
        return route(
                GET("/today"),
                req -> ok().body(Mono.just(Instant.now()), Instant.class)
        );
    }
}
