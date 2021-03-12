package com.imadelfetouh.authservice.logic.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class RabbitConnection {

    protected ConnectionFactory connectionFactory = new ConnectionFactory();
    protected final String queueName = "signin_queue";

    public RabbitConnection() {
        connectionFactory.setHost("localhost");
    }
}
