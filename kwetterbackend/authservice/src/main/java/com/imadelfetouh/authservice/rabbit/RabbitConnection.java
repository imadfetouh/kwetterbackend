package com.imadelfetouh.authservice.rabbit;

import com.imadelfetouh.authservice.environment.Environment;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitConnection {

    private ConnectionFactory connectionFactory;

    public RabbitConnection() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
    }

    public void sendNewUserProfileNotification(String username) {

        String queueName = Environment.getInstance().getRabbitProperties().getProperty("newUserProfileQueue");

        try(Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel()){

            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, username.getBytes());
        }
        catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }
}
