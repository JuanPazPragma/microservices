package com.example.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableFeignClients
@EnableEurekaClient
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@SpringBootApplication
public class PlazaComidaUsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlazaComidaUsuariosApplication.class, args);
    }

}
