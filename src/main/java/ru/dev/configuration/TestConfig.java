package ru.dev.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dev.auth.service.JwtService;

@Configuration
public class TestConfig {
    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }
}
