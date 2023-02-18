package com.example.plaza_comidas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class PlazaComidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlazaComidaApplication.class, args);
	}

}
