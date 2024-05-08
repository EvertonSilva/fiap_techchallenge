package br.com.fiap.techchallengelanchonete.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;

class CategoriaTest {
    
    @Test
    void deveCriarCategoriaVazia() {
        var categoria = new Categoria();

        assertAll("Atributos vazios", 
            () -> assertThat(categoria).isNotNull(),
            () -> assertThat(categoria.getId()).isNotNull(),
            () -> assertThat(categoria.getNome()).isNotNull()
        );
    }

}
