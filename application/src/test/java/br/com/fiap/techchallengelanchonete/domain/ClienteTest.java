package br.com.fiap.techchallengelanchonete.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;

public class ClienteTest {
    
    @Test
    void deveCriarClienteVazio() {
        var cliente = new Cliente();

        assertAll("Atributos vazios", 
            () -> assertThat(cliente).isNotNull(),
            () -> assertThat(cliente.getId()).isNotNull(),
            () -> assertThat(cliente.getCpf()).isNotNull(),
            () -> assertThat(cliente.getNome()).isNotNull(),
            () -> assertThat(cliente.getEmail()).isNotNull(),
            () -> assertThat(cliente.getPrimeiroNome()).isBlank(),
            () -> assertThat(cliente.getSobrenomes()).isBlank()
        );
    }

}
