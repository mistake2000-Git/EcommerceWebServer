package com.ecommerce.ecommerceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.Entity;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages ={"com.ecommerce.ecommerceapp.model"})
public class ECommerceAppApplication {
    private static ConfigurableApplicationContext applicationContext;
    public static void main(String[] args) {
         SpringApplication.run(ECommerceAppApplication.class, args);
    }
}
