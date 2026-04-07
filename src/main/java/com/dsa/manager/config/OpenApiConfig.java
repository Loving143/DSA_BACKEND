package com.dsa.manager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI dsaManagerOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("DSA Question Manager API")
                .description("Backend API for managing DSA topics, questions, answers, notes and tags")
                .version("1.0.0"));
    }
}
