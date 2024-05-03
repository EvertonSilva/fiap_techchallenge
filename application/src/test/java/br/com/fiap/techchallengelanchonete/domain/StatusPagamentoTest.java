package br.com.fiap.techchallengelanchonete.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;

public class StatusPagamentoTest {
    
    @ParameterizedTest
    @ValueSource(strings = { "AGUARDANDO", "APROVADO", "REPROVADO", "aguardando", "aprovado", "reprovado" })
    void deveCriarStatusPagamentoPorString(String statusPagamentoTexto) {
        var statusPagamento = StatusPagamento.de(statusPagamentoTexto);

        assertThat(statusPagamento)
            .isNotNull()
            .isInstanceOf(StatusPagamento.class);

        assertTrue(Arrays.asList(StatusPagamento.values()).contains(statusPagamento));
    }

    @ParameterizedTest
    @ValueSource(strings = { "bananinha", "" })
    void deveCriarStatusPagamentoPorString_quandoStringInvalida(String statusPagamentoTexto) {
        var ApplicationException = assertThrows(ApplicationException.class, () -> {
            StatusPagamento.de(statusPagamentoTexto);
        });

        assertThat(ApplicationException)
            .isNotNull()
            .isInstanceOf(ApplicationException.class);
        assertThat(ApplicationException.getMessage())
            .isEqualTo("Status não existe");
    }

    @Test
    void deveCriarStatusPagamentoPorString_quandoStringNula() {
        var ApplicationException = assertThrows(ApplicationException.class, () -> {
            StatusPagamento.de(null);
        });

        assertThat(ApplicationException)
            .isNotNull()
            .isInstanceOf(ApplicationException.class);
        assertThat(ApplicationException.getMessage())
            .isEqualTo("Status não existe");
    }

}
