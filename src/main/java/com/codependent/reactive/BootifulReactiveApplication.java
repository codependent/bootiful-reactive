package com.codependent.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.Request;
import org.springframework.web.reactive.function.RequestPredicates;
import org.springframework.web.reactive.function.Response;
import org.springframework.web.reactive.function.RouterFunction;
import org.springframework.web.reactive.function.RouterFunctions;

import reactor.core.publisher.Flux;
import reactor.ipc.netty.http.HttpServer;

@SpringBootApplication
@EnableMongoRepositories
public class BootifulReactiveApplication {

	@Bean
	RouterFunction<?> routerFunction(){
		return RouterFunctions.route(RequestPredicates.GET("/persons"), request -> null)
				.and(RouterFunctions.route(RequestPredicates.GET("/persons/{id}"), request -> null));
	}
	
	@Bean
	HttpServer httpServer(RouterFunction<?> routerFunction){
		HttpHandler handler = RouterFunctions.toHttpHandler(routerFunction);
		HttpServer httpServer = HttpServer.create(8080);
		httpServer.start(new ReactorHttpHandlerAdapter(handler));
		return httpServer;		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BootifulReactiveApplication.class, args);
	}
	
}
