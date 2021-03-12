package com.imadelfetouh.jwtservice.rabbit;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitConnection {

    private ConnectionFactory connectionFactory = new ConnectionFactory();
    private final String queueName = "jwt_queue";

    public RabbitConnection() {
        connectionFactory.setHost("localhost");
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public String getQueueName() {
        return queueName;
    }
}
