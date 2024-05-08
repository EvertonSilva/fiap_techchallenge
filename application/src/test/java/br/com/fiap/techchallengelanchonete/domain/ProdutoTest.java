package br.com.fiap.techchallengelanchonete.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.Produto;

class ProdutoTest {
    
    @Test
    void deveCriarProdutoVazio() {
        var produto = new Produto();

        assertAll("Atributos vazios", 
            () -> assertThat(produto).isNotNull(),
            () -> assertThat(produto.getId()).isNotNull(),
            () -> assertThat(produto.getNome()).isNotNull(),
            () -> assertThat(produto.getDescricao()).isNotNull(),
            () -> assertThat(produto.getPreco()).isNotNull(),
            () -> assertThat(produto.getCategoria()).isNotNull(),
            () -> assertThat(produto.getImagens()).isNotNull()
        );
    }

}
