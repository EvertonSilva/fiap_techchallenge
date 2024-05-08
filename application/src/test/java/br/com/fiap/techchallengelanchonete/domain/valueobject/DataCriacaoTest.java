package br.com.fiap.techchallengelanchonete.domain.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Instant;

import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataCriacao;

class DataCriacaoTest {
    
    @Test
    void deveGerarDataCriacaoValida() {
        var dataCriacao = new DataCriacao();
        verificaDataCriacaoVazia(dataCriacao);
        
        assertThat(dataCriacao.getValor())
            .isBefore(Instant.now());
    }

    @Test
    void deveGerarDataCriacaoDiferentes() throws InterruptedException {
        var dataCriacaoA = new DataCriacao();
        verificaDataCriacaoVazia(dataCriacaoA);

        Awaitility.await().pollDelay(Durations.ONE_SECOND).until(() -> true);

        var dataCriacaoB = new DataCriacao();
        verificaDataCriacaoVazia(dataCriacaoB);

        assertNotEquals(dataCriacaoA.getValor().getTime(), dataCriacaoB.getValor().getTime());
    }

    private void verificaDataCriacaoVazia(DataCriacao dataCriacao) {
        assertThat(dataCriacao)
            .isNotNull();
        assertThat(dataCriacao.getValor())
            .isNotNull();
        assertThat(dataCriacao.getValor().getTime())
            .isPositive();
    }

}
