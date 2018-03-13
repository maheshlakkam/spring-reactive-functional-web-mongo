package com.app.springreactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import com.app.springreactive.handler.RouterHandler;

@Configuration
public class RouterConfig {
	@Bean
	RouterFunction<?> routerFunction(RouterHandler handler) {
		return RouterFunctions.route(RequestPredicates.GET("/product/all"), handler::fetchAll)
				.andRoute(RequestPredicates.PUT("/product/insert").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::insertProduct)
				.andRoute(RequestPredicates.DELETE("/product/delete/{productId}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::deleteProductById)
				.andRoute(RequestPredicates.GET("/product/events/{productId}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::fetchProductEvents);
	}
}