package com.sparta.basic4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Basic4Application {

    public static void main(String[] args) {
        SpringApplication.run(Basic4Application.class, args);
    }

}
