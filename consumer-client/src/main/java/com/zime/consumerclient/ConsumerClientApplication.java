package com.zime.consumerclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zime.consumerclient.mapper")
public class ConsumerClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerClientApplication.class, args);
    }

}
