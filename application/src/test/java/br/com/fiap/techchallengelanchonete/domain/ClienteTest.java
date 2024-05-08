package br.com.fiap.techchallengelanchonete.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;

class ClienteTest {
    
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

    @Test 
    void deveRetornarPrimeiroNome_quandoNomeNulo() {
        var cliente = new Cliente();
        cliente.setNome(null);

        assertThat(cliente.getPrimeiroNome())
            .isNotNull();
    }

    @Test 
    void deveRetornarSobrenomes_quandoNomeNulo() {
        var cliente = new Cliente();
        cliente.setNome(null);
        
        assertThat(cliente.getSobrenomes())
            .isNotNull();
    }

}
