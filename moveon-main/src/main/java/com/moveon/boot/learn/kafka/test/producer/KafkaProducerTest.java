package com.moveon.boot.learn.kafka.test.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName KafkaProducerTest
 * @Description TODO
 * @Author Moveon
 * @Date 2024/8/21 14:03
 * @Version 1.0
 **/
public class KafkaProducerTest {
    public static void main(String[] args) {

        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<>(config);
        ProducerRecord<String, String> record = new ProducerRecord<>(
                "test",
                "key",
                "value"
        );
    }
}
