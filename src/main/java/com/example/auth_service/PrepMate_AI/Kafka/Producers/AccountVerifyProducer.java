package com.example.auth_service.PrepMate_AI.Kafka.Producers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AccountVerifyProducer
{
    private final KafkaTemplate<String, String> kafkaTemplate;

    public AccountVerifyProducer(KafkaTemplate<String, String> kafkaTemplate)
    {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, String>> sendVerificationMessage(
            String userId, String email, String token) {

        String message = String.format(
                "{\"userId\":\"%s\", \"email\":\"%s\", \"token\":\"%s\"}",
                userId, email, token);

        return kafkaTemplate.send("verify-account", userId, message);
    }
}
