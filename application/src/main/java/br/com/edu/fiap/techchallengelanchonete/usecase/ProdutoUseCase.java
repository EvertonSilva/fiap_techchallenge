package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ICategoriaPersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IProdutoPersistence;

import java.util.List;

public class ProdutoUseCase {
    private IProdutoPersistence produtoPersistence;
    private ICategoriaPersistence categoriaPersistence;

    public ProdutoUseCase(IProdutoPersistence produtoPersistence, ICategoriaPersistence categoriaPersistence) {
        this.produtoPersistence = produtoPersistence;
        this.categoriaPersistence = categoriaPersistence;
    }

    public Produto saveProduto(Produto produto) {
        var categoriaExistente = false;
        var categoriaInformada = produto.getCategoria() != null && produto.getCategoria().getId() != null;
        if (categoriaInformada) {
            Categoria categoriaBuscada = this.categoriaPersistence.buscaCategoria(produto.getCategoria().getId().getValor());
            categoriaExistente = categoriaBuscada.getId() != null;
        }

        if (!categoriaInformada || !categoriaExistente)
            throw new ApplicationException("Categoria não existe!");

        return produtoPersistence.cadastro(produto);
    }

    public List<Produto> getAllProdutos() {
        return produtoPersistence.listagem();
    }

    public Produto getProdutoById(Long id) {
        var optionalProduto = produtoPersistence.buscaId(id);

        return optionalProduto.isPresent() ?
                optionalProduto.get() : null;
    }

    public List<Produto> getProdutoByCategoria(String descricaoCategoria) {
        return produtoPersistence.buscaCategoria(descricaoCategoria);
    }
}
