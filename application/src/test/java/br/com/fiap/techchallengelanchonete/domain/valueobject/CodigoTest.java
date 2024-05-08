package br.com.fiap.techchallengelanchonete.domain.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;

class CodigoTest {
    
    @Test
    void deveGerarCodigoValido() {
        var codigo = new Codigo();
        verificaCodigoVazio(codigo);
        
        UUID uuidValido = UUID.fromString(codigo.getValor());
        assertThat(uuidValido)
            .isNotNull();
    }

    @Test
    void deveGerarCodigoSemColisao() {
        var codigoA = new Codigo();
        verificaCodigoVazio(codigoA);

        var codigoB = new Codigo();
        verificaCodigoVazio(codigoB);

        assertNotEquals(codigoA.getValor(), codigoB.getValor());
    }

    private void verificaCodigoVazio(Codigo codigo) {
        assertThat(codigo)
            .isNotNull();
        assertThat(codigo.getValor())
            .isNotBlank();
    }

}
