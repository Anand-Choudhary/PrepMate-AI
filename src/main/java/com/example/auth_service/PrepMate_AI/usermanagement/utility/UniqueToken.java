package com.example.auth_service.PrepMate_AI.usermanagement.utility;

import com.example.auth_service.PrepMate_AI.usermanagement.db.dao.UserDao;
import com.example.auth_service.PrepMate_AI.usermanagement.db.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class UniqueToken
{
    @Bean
    public String createUniqueToken()
    {
        return String.valueOf(UUID.randomUUID());
    }
}
