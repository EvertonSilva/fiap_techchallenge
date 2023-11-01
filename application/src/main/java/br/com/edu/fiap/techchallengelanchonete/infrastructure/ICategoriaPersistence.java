package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;

public interface ICategoriaPersistence {
    Categoria buscaCategoria(Long id);
}
