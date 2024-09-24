package com.sparta.basic4.config.auth;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().components(new Components())
                .info(swaggerInfo());
    }

    private Info swaggerInfo() {
        return new Info()
                .title("Basic 4회차 Swagger 프로젝트")
                .description("Spring 심화 과정 Basic 반 4회차 프로젝트 입니다.")
                .version("1.0.0");
    }
 }
