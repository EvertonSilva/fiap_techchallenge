package br.com.edu.fiap.techchallengelanchonete.infrastructure.categoria;

import br.com.edu.fiap.techchallengelanchonete.adapter.CategoriaAdapter;
import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ICategoriaPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class CategoriaAdapterJPA implements ICategoriaPersistence {
    private CategoriaRepository categoriaRepository;
    private CategoriaAdapter categoriaAdapter;

    @Autowired
    public CategoriaAdapterJPA(CategoriaRepository categoriaRepository, CategoriaAdapter categoriaAdapter) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaAdapter = categoriaAdapter;
    }

    public Optional<Categoria> buscaCategoria(Long id) {
        return this.categoriaRepository
                .findById(id)
                .map(categoriaAdapter::toDomain);
    }
}
