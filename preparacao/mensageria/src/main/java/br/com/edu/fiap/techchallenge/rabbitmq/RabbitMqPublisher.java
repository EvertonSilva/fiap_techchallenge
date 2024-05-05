package br.com.edu.fiap.techchallenge.rabbitmq;

import java.io.IOException;

public class RabbitMqPublisher extends RabbitMqActor {

    public RabbitMqPublisher() throws IOException {
        super();
    }

    public void send(String message, String queueName) throws IOException {
        subscribe(queueName);
        channel.basicPublish("", queueName, null, message.getBytes());
    }
}
