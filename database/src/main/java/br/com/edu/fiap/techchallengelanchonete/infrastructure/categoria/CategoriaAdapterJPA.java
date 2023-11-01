package br.com.edu.fiap.techchallengelanchonete.infrastructure.categoria;

import br.com.edu.fiap.techchallengelanchonete.adapter.CategoriaAdapter;
import br.com.edu.fiap.techchallengelanchonete.adapter.ProdutoAdapter;
import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ICategoriaPersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IProdutoPersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaAdapterJPA implements ICategoriaPersistence {
    private CategoriaRepository categoriaRepository;
    private CategoriaAdapter categoriaAdapter;

    @Autowired
    public CategoriaAdapterJPA(CategoriaRepository categoriaRepository, CategoriaAdapter categoriaAdapter) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaAdapter = categoriaAdapter;
    }

    public Categoria buscaCategoria(Long id) {
        var optionalCategoria = this.categoriaRepository.findById(id);

        return optionalCategoria.isPresent() ?
                this.categoriaAdapter.toDomain(optionalCategoria.get()) : new Categoria();
    }
}
