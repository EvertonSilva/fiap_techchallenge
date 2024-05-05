package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;

import java.util.Optional;

public interface ICategoriaPersistence {
    Optional<Categoria> buscaCategoria(Long id);
}
