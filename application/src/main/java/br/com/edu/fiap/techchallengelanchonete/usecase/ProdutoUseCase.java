package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ProdutoModel;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoUseCase {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaUseCase categoriaUseCase;

    public Produto saveProduto(Produto produto) {
        ProdutoModel model = this.adapterToModel(produto);
        return adapterToProduto(produtoRepository.save(model));
    }

    public List<Produto> getAllProdutos() {
        List<ProdutoModel> produtosModel = produtoRepository.findAll();
        List<Produto> produtos = new ArrayList<>();
        produtosModel.forEach(p -> produtos.add(adapterToProduto(p)));
        return produtos;
    }

    public Produto getProdutoById(Long id) {
        ProdutoModel model = produtoRepository.findById(id).get();
        Produto produto = this.adapterToProduto(model);
        return produto;
    }

    public List<Produto> getProdutoByCategoria(String descricaoCategoria) {
        List<ProdutoModel> produtosModel = produtoRepository.findByCategoria_Nome(descricaoCategoria);
        List<Produto> produtos = new ArrayList();
        produtosModel.forEach(p -> produtos.add(adapterToProduto(p)));
        return produtos;
    }
    private ProdutoModel adapterToModel(Produto produto) {
        ProdutoModel model = new ProdutoModel();
        model.setNome(produto.getNome().getValor());
        model.setDescricao(produto.getDescricao());
        model.setPreco(produto.getPreco());
        model.setCategoria(categoriaUseCase.adapterToModel(produto.getCategoria()));
        return model;
    }

    private Produto adapterToProduto(ProdutoModel model) {
        Produto produto = new Produto();
        produto.setNome(new Nome(model.getNome()));
        produto.setDescricao(model.getDescricao());
        produto.setPreco(model.getPreco());
        produto.setCategoria(categoriaUseCase.adapterToCategoria(model.getCategoria()));
        return produto;
    }
}
