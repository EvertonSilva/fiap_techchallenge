package br.com.edu.fiap.techchallengelanchonete.infrastructure.produto;

import br.com.edu.fiap.techchallengelanchonete.adapter.ProdutoAdapter;
import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.exception.NotFoundResourceException;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IProdutoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProdutoAdapterJPA implements IProdutoPersistence {

    private ProdutoRepository produtoRepository;
    private ProdutoAdapter produtoAdapter;

    @Autowired
    public ProdutoAdapterJPA (ProdutoRepository produtoRepository, ProdutoAdapter produtoAdapter) {
        this.produtoRepository = produtoRepository;
        this.produtoAdapter = produtoAdapter;
    }

    @Override
    public Produto cadastro(Produto produto) {
        var produtoModel = this.produtoAdapter.toModel(produto);
        return this.produtoAdapter.toDomain(this.produtoRepository.save(produtoModel));
    }

    @Override
    public Produto edita(Long id, Produto produto) {
        var produtoBanco = this.produtoRepository.findById(id);

        if (!produtoBanco.isPresent())
            throw new NotFoundResourceException("Produto não encontrado!");

        produto.setId(new Id(id));
        var produtoModel = this.produtoAdapter.toModel(produto);
        return this.produtoAdapter.toDomain(this.produtoRepository.save(produtoModel));
    }

    @Override
    public void exclui(Long id) {
        this.produtoRepository.deleteById(id);
    }

    @Override
    public List<Produto> listagem() {
        return this.produtoRepository
                .findAll()
                .stream()
                .map(x -> this.produtoAdapter.toDomain(x))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Produto> buscaId(Long id) {
        return this.produtoRepository
                .findById(id)
                .map(produtoAdapter::toDomain);
    }

    @Override
    public List<Produto> buscaCategoria(String descricao) {
        return this.produtoRepository
                .findByCategoria_Nome(descricao.toUpperCase())
                .stream()
                .map(x -> this.produtoAdapter.toDomain(x))
                .collect(Collectors.toList());
    }
}
