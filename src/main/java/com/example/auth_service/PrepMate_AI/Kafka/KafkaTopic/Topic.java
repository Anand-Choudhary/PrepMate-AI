package com.example.auth_service.PrepMate_AI.Kafka.KafkaTopic;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Topic
{
    @Bean
    public NewTopic accountVerify()
    {
        return new NewTopic("verify-account", 3, (short) 1);
    }
}
