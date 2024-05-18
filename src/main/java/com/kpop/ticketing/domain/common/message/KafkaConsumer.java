package com.kpop.ticketing.domain.common.message;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "reservation-topic", groupId = "reservation-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
