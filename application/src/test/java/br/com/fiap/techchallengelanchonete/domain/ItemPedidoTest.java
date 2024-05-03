package br.com.fiap.techchallengelanchonete.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.ItemPedido;

public class ItemPedidoTest {
    
    @Test
    void deveCriarItemPedidoVazio() {
        var itemPedido = new ItemPedido();

        assertAll("Atributos vazios", 
            () -> assertThat(itemPedido).isNotNull(),
            () -> assertThat(itemPedido.getId()).isNotNull(),
            () -> assertThat(itemPedido.getQuantidade()).isNotNull(),
            () -> assertThat(itemPedido.getProduto()).isNotNull(),
            () -> assertThat(itemPedido.getSubTotal()).isNotNull()
        );
    }

}
