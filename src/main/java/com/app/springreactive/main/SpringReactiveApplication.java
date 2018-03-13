package com.app.springreactive.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
@EnableReactiveMongoRepositories(basePackages="com.app.springreactive.repository")
@ComponentScan(basePackages="com.app.springreactive")
@SpringBootApplication
public class SpringReactiveApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveApplication.class, args);
	}
}
