package br.com.edu.fiap.techchallengelanchonete.adapter;

import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.categoria.CategoriaModel;
import org.springframework.stereotype.Component;

@Component
public class CategoriaAdapter implements IAdapter<Categoria, CategoriaModel> {
    @Override
    public Categoria toDomain(CategoriaModel categoriaModel) {
        if (categoriaModel == null)
            return null;

        Categoria categoria = new Categoria();

        categoria.setId(new Id(categoriaModel.getId()));
        categoria.setNome(new Nome(categoriaModel.getNome()));

        return categoria;
    }

    @Override
    public CategoriaModel toModel(Categoria categoria) {
        if (categoria == null)
            return null;

        CategoriaModel model = new CategoriaModel();

        if (categoria.getId() != null && categoria.getId().getValor() > 0)
            model.setId(categoria.getId().getValor());

        model.setNome(categoria.getNome().getValor());

        return model;
    }
}
