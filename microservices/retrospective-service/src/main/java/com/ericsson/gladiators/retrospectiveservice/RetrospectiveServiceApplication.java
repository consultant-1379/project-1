package com.ericsson.gladiators.retrospectiveservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableEurekaClient
public class RetrospectiveServiceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RetrospectiveServiceApplication.class, args);
	}

}

	//need to add some mock items


