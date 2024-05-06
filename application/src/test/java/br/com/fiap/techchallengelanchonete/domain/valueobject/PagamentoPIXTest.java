package br.com.fiap.techchallengelanchonete.domain.valueobject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoCopiaCola;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoQRCode;

class PagamentoPIXTest {
    
    @Test
    void deveCriarPagamentoCopiaCola() {
        assertThat(new PagamentoCopiaCola())
            .isNotNull();
    }

    @Test
    void deveCriarPagamentoQRCode() {
        assertThat(new PagamentoQRCode())
            .isNotNull();
    }

}
