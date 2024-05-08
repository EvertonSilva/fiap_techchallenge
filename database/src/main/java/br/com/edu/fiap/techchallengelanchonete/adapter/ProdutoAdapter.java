package br.com.edu.fiap.techchallengelanchonete.adapter;

import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Descricao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.produto.ProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoAdapter implements IAdapter<Produto, ProdutoModel> {

    private CategoriaAdapter categoriaAdapter;

    @Autowired
    public ProdutoAdapter(CategoriaAdapter categoriaAdapter) {
        this.categoriaAdapter = categoriaAdapter;
    }

    @Override
    public Produto toDomain(ProdutoModel produtoModel) {
        if (produtoModel == null)
            return null;

        var produto = new Produto();

        produto.setId(new Id(produtoModel.getId()));
        produto.setNome(new Nome(produtoModel.getNome()));
        produto.setDescricao(new Descricao(produtoModel.getDescricao()));
        produto.setPreco(new Valor(produtoModel.getPreco()));
        produto.setCategoria(this.categoriaAdapter.toDomain(produtoModel.getCategoria()));

        return produto;
    }

    @Override
    public ProdutoModel toModel(Produto produto) {
        if (produto == null)
            return null;

        var produtoModel = new ProdutoModel();

        if (produto.getId() != null && produto.getId().getValor() > 0)
            produtoModel.setId(produto.getId().getValor());

        produtoModel.setNome(produto.getNome().getValor());
        produtoModel.setDescricao(produto.getDescricao().getValor());
        produtoModel.setPreco(produto.getPreco().getValor());
        produtoModel.setCategoria(this.categoriaAdapter.toModel(produto.getCategoria()));

        return produtoModel;
    }
}
