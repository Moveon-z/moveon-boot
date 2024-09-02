package com.moveon.boot.learn.kafka.test.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName KafkaConsumerTest
 * @Description TODO
 * @Author Moveon
 * @Date 2024/8/21 14:13
 * @Version 1.0
 **/
public class KafkaConsumerTest {
    public static void main(String[] args) {
        Map<String, String> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "moveon");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config);
        consumer.subscribe(Collections.singletonList("test"));
    }
}
