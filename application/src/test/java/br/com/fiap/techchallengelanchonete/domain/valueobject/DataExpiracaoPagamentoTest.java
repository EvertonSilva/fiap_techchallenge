package br.com.fiap.techchallengelanchonete.domain.valueobject;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataExpiracao;

class DataExpiracaoPagamentoTest {
    
    @Test
    void deveGerarDataPadraoExpiracaoDe30Minutos() throws InterruptedException {
        var dataExpeiracaoPadraoPagamento = DataExpiracao.ExpiracaoPadraoPagamento();
        var trintaMinutosNoFuturo = Instant.now().plus(30, ChronoUnit.MINUTES);

        assertThat(dataExpeiracaoPadraoPagamento)
            .isNotNull();
        assertThat(dataExpeiracaoPadraoPagamento.getData())
            .isNotNull();
        assertThat(dataExpeiracaoPadraoPagamento.getData().toInstant().truncatedTo(ChronoUnit.SECONDS))
            .isEqualTo(trintaMinutosNoFuturo.truncatedTo(ChronoUnit.SECONDS));
    }

}
