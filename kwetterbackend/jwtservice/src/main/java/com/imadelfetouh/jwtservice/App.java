package com.imadelfetouh.jwtservice;

import com.google.gson.Gson;
import com.imadelfetouh.jwtservice.jwt.CreateJWTToken;
import com.imadelfetouh.jwtservice.rabbit.RabbitConnection;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class App {
    private static final String exchange = "newtoken_exchange";

    public static void main(String[] args) {

        Object monitor = new Object();
        RabbitConnection rabbitConnection = new RabbitConnection();
        ConnectionFactory connectionFactory = rabbitConnection.getConnectionFactory();

        try(Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(exchange, "direct");
            channel.queueBind(rabbitConnection.getQueueName(), exchange, "");

            DeliverCallback deliverCallback = (s, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("received id: " + message);

                Map<String, String> claims = new HashMap<>();
                claims.put("userId", message);
                String token = CreateJWTToken.getInstance().create(claims);

                sendJwt(channel, delivery.getProperties().getReplyTo(), token);
            };

            channel.basicConsume(rabbitConnection.getQueueName(), true, deliverCallback, s -> {});

            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendJwt(Channel channel, String routingkey, String token) {
        try {
            channel.basicPublish(exchange, routingkey, null, token.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
