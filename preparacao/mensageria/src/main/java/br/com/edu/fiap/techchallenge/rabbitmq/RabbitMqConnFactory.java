package br.com.edu.fiap.techchallenge.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

public class RabbitMqConnFactory {
    private static Connection connection;


    public static Connection getConnection() {
        if (connection == null || !connection.isOpen()) {
            try {
                var factory = new ConnectionFactory();
                factory.setHost("localhost");
                factory.setUsername("fiap");
                factory.setPassword("Rabbit!1@2#3");
                connection = factory.newConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
