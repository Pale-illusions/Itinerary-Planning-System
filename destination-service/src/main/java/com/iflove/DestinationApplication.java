package com.iflove;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.iflove.mapper")
@SpringBootApplication
public class DestinationApplication {
    public static void main(String[] args) {
        SpringApplication.run(DestinationApplication.class, args);
    }
}

// TODO sentinel Feign 配置