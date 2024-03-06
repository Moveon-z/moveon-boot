package com.moveon.boot.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyListener
 * @Description TODO
 * @Author Moveon
 * @Date 2024/3/5 10:05
 * @Version 1.0
 **/
@Component
public class MyListener {

    @RabbitListener(queues = "")
    public void handleMessage(String message, Message messageBody) {

    }
}
