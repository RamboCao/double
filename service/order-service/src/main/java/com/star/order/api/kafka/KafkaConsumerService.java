package com.star.order.api.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(groupId = "1", topics = "topic-test")
    public void listen(String data){
        System.out.println(data);
    }
}
