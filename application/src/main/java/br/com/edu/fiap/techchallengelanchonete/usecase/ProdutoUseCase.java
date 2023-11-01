package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IProdutoPersistence;

import java.util.List;

public class ProdutoUseCase {
    private IProdutoPersistence produtoPersistence;

    public ProdutoUseCase(IProdutoPersistence produtoPersistence) {
        this.produtoPersistence = produtoPersistence;
    }

    public Produto saveProduto(Produto produto) {
        return produtoPersistence.cadastro(produto);
    }

    public List<Produto> getAllProdutos() {
        return produtoPersistence.listagem();
    }

    public Produto getProdutoById(Long id) {
        var optionalProduto = produtoPersistence.buscaId(id);

        return optionalProduto.isPresent() ?
                optionalProduto.get() : new Produto();
    }

    public List<Produto> getProdutoByCategoria(String descricaoCategoria) {
        return produtoPersistence.buscaCategoria(descricaoCategoria);
    }
}
