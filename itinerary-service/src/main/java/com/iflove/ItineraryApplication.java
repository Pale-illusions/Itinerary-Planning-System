package com.iflove;

import com.iflove.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(value = "com.iflove.api.client", defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.iflove.mapper")
@SpringBootApplication
public class ItineraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItineraryApplication.class, args);
    }
}
