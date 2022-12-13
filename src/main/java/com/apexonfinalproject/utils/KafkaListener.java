package com.apexonfinalproject.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(topics = "order-events")
    public void listen(Message message) {
        String payload = (String) message.getPayload();
        log.info("Received message: " + payload);
    }

}
