package br.com.edu.fiap.techchallenge.rabbitmq;

import com.rabbitmq.client.Channel;

import java.io.IOException;

public abstract class RabbitMqActor {
    protected Channel channel;

    public RabbitMqActor() throws IOException {
        var connection = RabbitMqConnFactory.getConnection();
        if (channel == null || !channel.isOpen()) {
            channel = connection.createChannel();
        }
    }

    protected void subscribe(String queue) throws IOException {
        channel.queueDeclare(queue, false, false, false, null);
    }
}
