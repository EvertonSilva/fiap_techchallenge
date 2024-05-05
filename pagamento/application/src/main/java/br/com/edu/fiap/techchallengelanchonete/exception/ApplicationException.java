package br.com.edu.fiap.techchallengelanchonete.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException (String mensagem) {
        super(mensagem);
    }
}
