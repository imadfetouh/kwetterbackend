package com.imadelfetouh.authservice.logic.rabbit;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RabbitJWT extends RabbitConnection{

    private final String routingKey = UUID.randomUUID().toString();
    private final Integer userId;
    private final String exchange = "newtoken_exchange";

    public RabbitJWT(Integer userId) {
        super();
        this.userId = userId;
    }

    public String getToken() {
        String token = null;
        try(Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(exchange, "direct");
            channel.queueBind(queueName, exchange, routingKey);

            BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    blockingQueue.offer(new String(body, "UTF-8"));
                }
            };

            channel.basicConsume(queueName, true, consumer);

            sendMessage(channel);

            token = blockingQueue.poll(3000, TimeUnit.MILLISECONDS);

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return token;
    }

    private void sendMessage(Channel channel) {
        try {
            AMQP.BasicProperties properties = new AMQP.BasicProperties()
                    .builder()
                    .replyTo(routingKey)
                    .build();

            channel.basicPublish(exchange, "", properties, userId.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
