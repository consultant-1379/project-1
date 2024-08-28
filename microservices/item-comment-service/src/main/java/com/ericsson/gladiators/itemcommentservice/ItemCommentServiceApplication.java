package com.ericsson.gladiators.itemcommentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ItemCommentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemCommentServiceApplication.class, args);
	}

}
