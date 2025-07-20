package com.example.auth_service.PrepMate_AI.usermanagement.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoderConfig
{
    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
