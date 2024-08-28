package com.ericsson.gladiators.apigatewayservice;

import com.ericsson.gladiators.apigatewayservice.filters.ErrorLoggerFilter;
import com.ericsson.gladiators.apigatewayservice.filters.LoggerFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy	//enables the proxy (API gateway functionality)
@EnableEurekaClient	//allows for the connection to the Eureka discovery service
public class ApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServiceApplication.class, args);
	}

	@Bean
	public ErrorLoggerFilter errorLoggerFilter() {
		return new ErrorLoggerFilter();
	}

	@Bean
	public LoggerFilter loggerFilter() {
		return new LoggerFilter();
	}

}
