package com.poly.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoveBgConfig {
    @Value("${removebg.apiKey}")
    private String apiKey;

    @Value("${removebg.baseUrl}")
    private String baseUrl;

    @Bean
    public String removeBgApiKey() {
        return apiKey;
    }

    @Bean
    public String removeBgBaseUrl() {
        return baseUrl;
    }
}
