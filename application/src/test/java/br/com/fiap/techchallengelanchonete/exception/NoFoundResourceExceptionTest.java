package br.com.fiap.techchallengelanchonete.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.exception.NotFoundResourceException;

class NoFoundResourceExceptionTest {
    
    @Test
    void deveGerarNoFoundResourceException() {
        var msgException = "NotFoundResourceException de teste!";

        var notFoundResourceException = assertThrows(NotFoundResourceException.class, () -> {
            throw new NotFoundResourceException(msgException);
        } );
        
        assertThat(notFoundResourceException)
            .isNotNull()
            .isInstanceOf( NotFoundResourceException.class);
        
        assertThat(notFoundResourceException.getMessage())
            .isNotBlank()
            .isEqualTo(msgException);
    }

}
