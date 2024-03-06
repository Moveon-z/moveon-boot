package com.moveon.boot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RabbitMQConfig
 * @Description TODO
 * @Author Moveon
 * @Date 2024/3/4 14:56
 * @Version 1.0
 **/
@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue delayQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "event-exchange");
        arguments.put("x-dead-letter-routing-key", "delay-key");
        arguments.put("x-message-ttl", 30000);
        Queue queue = new Queue("delay.queue", true, false, false, arguments);
        return queue;
    }

    @Bean
    public Queue releaseQueue() {
        return new Queue("release.queue", true, false, false, null);
    }

    @Bean
    public Exchange orderEventExchange() {
        return new TopicExchange("event-exchange", true, false);
    }

    @Bean
    public Binding orderCreateOrderBinding() {
        return new Binding("delay.queue",
                Binding.DestinationType.QUEUE,
                "event-exchange",
                "delay.#",
                null);
    }

    @Bean
    public Binding orderReleaseOrderBinding() {
        return new Binding("release.queue",
                Binding.DestinationType.QUEUE,
                "event-exchange",
                "release.#",
                null);
    }
}
