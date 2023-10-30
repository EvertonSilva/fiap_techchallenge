package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.CategoriaModel;
import org.springframework.stereotype.Component;

@Component
public class CategoriaUseCase {

    public CategoriaModel adapterToModel(Categoria categoria) {
        CategoriaModel model = new CategoriaModel();
        model.setId(categoria.getId());
        return model;
    }
    public Categoria adapterToCategoria(CategoriaModel model) {
        Categoria categoria = new Categoria();
        categoria.setId(model.getId());
        categoria.setNome(new Nome(model.getNome()));
        return categoria;
    }
}
