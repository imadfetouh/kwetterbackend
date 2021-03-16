package com.imadelfetouh.authservice.logic.rabbit;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RabbitJWT{

    private static final Logger logger = Logger.getLogger(RabbitJWT.class.getName());
    private final String corrId;
    private final Integer userId;
    private final String requestQueueName;
    private Connection connection;

    public RabbitJWT(Integer userId) {
        this.corrId = UUID.randomUUID().toString();
        this.userId = userId;
        this.requestQueueName = "jwt_queue";
        connection = RabbitConnection.getInstance().getConnection();
    }

    public String getToken() {
        String token = null;
        try(Channel channel = connection.createChannel()) {

            String replyQueueName = channel.queueDeclare().getQueue();

            AMQP.BasicProperties properties = new AMQP.BasicProperties()
                    .builder()
                    .correlationId(corrId)
                    .replyTo(replyQueueName)
                    .build();

            BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1);
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    if(properties.getCorrelationId().equals(corrId)){
                        blockingQueue.offer(new String(body, "UTF-8"));
                    }
                }
            };

            channel.basicConsume(replyQueueName, true, consumer);

            channel.basicPublish("", requestQueueName, properties, userId.toString().getBytes());

            token = blockingQueue.poll(3000, TimeUnit.MILLISECONDS);

        } catch (TimeoutException | IOException | InterruptedException e) {
            logger.log(Level.ALL, e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                logger.log(Level.ALL, e.getMessage());
            }
        }

        return token;
    }
}
