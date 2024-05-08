package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;
import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ICategoriaPersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IProdutoPersistence;

import java.util.List;
import java.util.Optional;

public class ProdutoUseCase {
    private IProdutoPersistence produtoPersistence;
    private ICategoriaPersistence categoriaPersistence;

    public ProdutoUseCase(IProdutoPersistence produtoPersistence, ICategoriaPersistence categoriaPersistence) {
        this.produtoPersistence = produtoPersistence;
        this.categoriaPersistence = categoriaPersistence;
    }

    public Produto salvaProduto(Produto produto) {
        var categoriaExistente = false;
        var categoriaInformada = 
            produto.getCategoria() != null 
            && produto.getCategoria().getId() != null
            && produto.getCategoria().getId().getValor() > 0;

        if (categoriaInformada) {
            Optional<Categoria> categoriaBuscada = this.categoriaPersistence.buscaCategoria(produto.getCategoria().getId().getValor());
            categoriaExistente = categoriaBuscada.isPresent();
        }

        if (!categoriaInformada || !categoriaExistente)
            throw new ApplicationException("Categoria não existe!");

        return produtoPersistence.cadastro(produto);
    }

    public List<Produto> listaProdutos(String descricaoCategoria) {
        return descricaoCategoria == null ?
                produtoPersistence.listagem() : produtoPersistence.buscaCategoria(descricaoCategoria);
    }

    public Optional<Produto> buscaPorId(Long id) {
        return produtoPersistence.buscaId(id);
    }
}
