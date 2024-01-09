package org.javibanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.javibanda.feign")
public class User {
    public static void main(String[] args) {
        SpringApplication.run(User.class);
    }
}