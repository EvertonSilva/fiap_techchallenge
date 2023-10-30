package br.com.edu.fiap.techchallengelanchonete.adapter;

import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ProdutoModel;
import org.springframework.stereotype.Component;

@Component
public class ProdutoAdapter implements IAdapter<Produto, ProdutoModel> {
    @Override
    public Produto toDomain(ProdutoModel produtoModel) {
        var produto = new Produto();

        produto.setId(produtoModel.getId());
        produto.setNome(new Nome(produtoModel.getNome()));
        // TODO: Implementar o restante quando o merge com a branch de produto acontecer.

        return produto;
    }

    @Override
    public ProdutoModel toModel(Produto produto) {
        var produtoModel = new ProdutoModel();

        produtoModel.setId(produto.getId());
        produtoModel.setNome(produto.getNome().getValor());
        // TODO: Implementar o restante quando o merge com a branch de produto acontecer.

        return produtoModel;
    }
}
