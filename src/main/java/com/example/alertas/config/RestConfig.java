package com.example.alertas.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Esto usa el cliente nativo de Spring Boot,
        // sin librerías externas que causen conflictos de TlsSocketStrategy
        return builder.build();
    }
}
