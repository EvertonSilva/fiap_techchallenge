package br.com.edu.fiap.techchallengelanchonete.exception;

public class NotFoundResourceException extends RuntimeException {
    public NotFoundResourceException(String mesangem) {
        super(mesangem);
    }
}
