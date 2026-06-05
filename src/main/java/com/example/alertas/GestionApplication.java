package com.example.alertas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionApplication.class, args);
	}
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
