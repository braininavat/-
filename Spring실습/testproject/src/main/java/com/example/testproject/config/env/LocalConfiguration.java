package com.example.testproject.config.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local")
@Configuration
public class LocalConfiguration implements EnvConfiguration{

    @Value("${testproject.loading.message}")
    private String message;
    @Override
    @Bean
    public String getMessage() {
        return message;
    }
}
