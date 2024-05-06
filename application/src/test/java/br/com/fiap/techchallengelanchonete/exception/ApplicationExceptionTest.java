package br.com.fiap.techchallengelanchonete.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;

class ApplicationExceptionTest {

    @Test
    void deveGerarApplicationException() {
        var msgException = "ApplicationException de teste!";

        var applicationException = assertThrows(ApplicationException.class, () -> {
            throw new ApplicationException(msgException);
        });
        
        assertThat(applicationException)
            .isNotNull()
            .isInstanceOf( ApplicationException.class);
        
        assertThat(applicationException.getMessage())
            .isNotBlank()
            .isEqualTo(msgException);
    }

}
