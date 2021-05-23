package com.casestudy.myeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MyEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEurekaServerApplication.class, args);
	}

}
