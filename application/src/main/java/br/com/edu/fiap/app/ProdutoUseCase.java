package br.com.edu.fiap.app;

import br.com.edu.fiap.domain.Produto;
import br.com.edu.fiap.infrastructure.ProdutoModel;
import br.com.edu.fiap.infrastructure.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoUseCase {
    @Autowired
    private ProdutoRepository repo;

    public void salvaProduto(Produto produto) {
        var model = this.adapter(produto);
        repo.save(model);
    }

    public Produto getProduto(Long id) {
        var model = repo.findById(id).get();
        var produto = this.adapterToProduto(model);
        return produto;
    }

    private ProdutoModel adapter(Produto produto) {
        var model = new ProdutoModel();
        model.setNome(produto.getNome());
        return model;
    }

    private Produto adapterToProduto(ProdutoModel model) {
        var produto = new Produto();
        produto.setNome(model.getNome());
        return produto;
    }
}
