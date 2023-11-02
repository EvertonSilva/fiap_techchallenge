package br.com.edu.fiap.techchallengelanchonete.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NotFoundResourceException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundException(NotFoundResourceException ex, WebRequest request) {
        return new ErrorMessage(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage applicationException(ApplicationException ex, WebRequest request) {
        return new ErrorMessage(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalException(Exception ex, WebRequest request) {
        return new ErrorMessage(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }
}
