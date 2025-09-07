package com.example.auth_service.PrepMate_AI.Kafka.Consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

public class KafkaConsumer
{
    @KafkaListener(topics = "my-topic", groupId = "custom-group")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        System.out.println("Processing record: " + record.value());

        // Process message logic to send mail.

        // Manually commit offset after processing
        acknowledgment.acknowledge();
    }
}
