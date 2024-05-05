package br.com.edu.fiap.techchallenge;

import br.com.edu.fiap.techchallenge.rabbitmq.RabbitMqConsumer;
import br.com.edu.fiap.techchallenge.rabbitmq.RabbitMqPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

class UseCaseMessageReceiver {
   public void execute(String message) {
       System.out.println("Message received: " + message);
   }
}

public class Main {
    private static final Logger logger
            = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting RabbitMQ consumer...");
        try {
            publish();
//            consume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void publish() throws IOException {
        var publisher = new RabbitMqPublisher();
        String message = "Hello, again world";
        publisher.send(message, "NOVO_PEDIDO");
        System.out.println(" [x] Sent '" + message + "'");
    }

    public static void consume() throws IOException {
        var consumer = new RabbitMqConsumer();
        var useCase = new UseCaseMessageReceiver();

        consumer.receive("NOVO_PEDIDO", useCase::execute);
    }
}
