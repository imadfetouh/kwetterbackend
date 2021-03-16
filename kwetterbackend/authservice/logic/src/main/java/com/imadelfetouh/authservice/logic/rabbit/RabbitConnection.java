package com.imadelfetouh.authservice.logic.rabbit;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitConnection {

    private static final Logger logger = Logger.getLogger(RabbitConnection.class.getName());

    private static RabbitConnection rabbitConnection = new RabbitConnection();
    private ConnectionFactory connectionFactory;
    private Connection connection;

    private RabbitConnection() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        createConnection();
    }

    public static RabbitConnection getInstance() {
        return rabbitConnection;
    }

    private Connection createConnection() {
        try {
            connection = connectionFactory.newConnection();
            return connection;
        } catch (TimeoutException | IOException e) {
            logger.log(Level.ALL, e.getMessage());
        }

        return null;
    }

    public Connection getConnection() {
        if(connection.isOpen()){
            return connection;
        }

        return createConnection();
    }
}
