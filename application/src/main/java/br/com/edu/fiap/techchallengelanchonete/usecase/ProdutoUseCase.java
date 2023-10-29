package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ProdutoModel;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoUseCase {
    @Autowired
    private ProdutoRepository repo;

    public void salvaProduto(Produto produto) {
        var model = this.adapterToModel(produto);
        System.out.println(produto.getNome());
        System.out.println(model.getNome());
        repo.save(model);
    }

    public Produto getProduto(Long id) {
        var model = repo.findById(id).get();
        var produto = this.adapterToProduto(model);
        return produto;
    }

    private ProdutoModel adapterToModel(Produto produto) {
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
