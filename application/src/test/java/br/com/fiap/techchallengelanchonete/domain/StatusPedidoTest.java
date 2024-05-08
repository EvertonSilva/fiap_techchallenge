package br.com.fiap.techchallengelanchonete.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import br.com.edu.fiap.techchallengelanchonete.domain.StatusPedido;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;

class StatusPedidoTest {

    @ParameterizedTest
    @ValueSource(
        strings = {
            "AGUARDANDO_PAGAMENTO", "RECEBIDO", "EM_PREPARACAO", "PRONTO", "FINALIZADO", "CANCELADO",
            "aguardando_pagamento", "recebido", "em_preparacao", "pronto", "finalizado", "cancelado"
        })
    void deveCriarStatusPedidoPorString(String statusPedidoTexto) {
        var statusPedido = StatusPedido.de(statusPedidoTexto);

        assertThat(statusPedido)
            .isNotNull()
            .isInstanceOf(StatusPedido.class);

        assertTrue(Arrays.asList(StatusPedido.values()).contains(statusPedido));
    }

    @ParameterizedTest
    @ValueSource(strings = { "bananinha", "" })
    void deveCriarStatusPedidoPorString_quandoStringInvalida(String statusPedidoTexto) {
        var applicationException = assertThrows(ApplicationException.class, () -> {
            StatusPedido.de(statusPedidoTexto);
        });

        assertThat(applicationException)
            .isNotNull()
            .isInstanceOf(ApplicationException.class);
        assertThat(applicationException.getMessage())
            .isEqualTo("Status não existe");
    }

    @Test
    void deveCriarStatusPedidoPorString_quandoStringNula() {
        var applicationException = assertThrows(ApplicationException.class, () -> {
            StatusPedido.de(null);
        });

        assertThat(applicationException)
            .isNotNull()
            .isInstanceOf(ApplicationException.class);
        assertThat(applicationException.getMessage())
            .isEqualTo("Status não existe");
    }

}
