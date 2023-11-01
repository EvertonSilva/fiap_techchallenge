// FIXME
//package br.com.edu.fiap.techchallengelanchonete.usecase;
//
//import br.com.edu.fiap.techchallengelanchonete.adapter.CategoriaAdapter;
//import br.com.edu.fiap.techchallengelanchonete.adapter.ProdutoAdapter;
//import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
//import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Descricao;
//import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
//import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;
//import br.com.edu.fiap.techchallengelanchonete.infrastructure.ProdutoModel;
//import br.com.edu.fiap.techchallengelanchonete.infrastructure.ProdutoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class ProdutoUseCase {
//    private ProdutoRepository produtoRepository;
//    private ProdutoAdapter produtoAdapter;
//    private CategoriaAdapter categoriaAdapter;
//
//    @Autowired
//    public ProdutoUseCase(ProdutoRepository produtoRepository, ProdutoAdapter produtoAdapter, CategoriaAdapter categoriaAdapter) {
//        this.produtoRepository = produtoRepository;
//        this.produtoAdapter = produtoAdapter;
//        this.categoriaAdapter = categoriaAdapter;
//    }
//
//    public Produto saveProduto(Produto produto) {
//        ProdutoModel model = this.produtoAdapter.toModel(produto);
//        return this.produtoAdapter.toDomain(produtoRepository.save(model));
//    }
//
//    public List<Produto> getAllProdutos() {
//        List<ProdutoModel> produtosModel = produtoRepository.findAll();
//        List<Produto> produtos = new ArrayList<>();
//        produtosModel.forEach(p -> produtos.add(this.produtoAdapter.toDomain(p)));
//        return produtos;
//    }
//
//    public Produto getProdutoById(Long id) {
//        ProdutoModel model = produtoRepository.findById(id).get();
//        Produto produto = this.produtoAdapter.toDomain(model);
//        return produto;
//    }
//
//    public List<Produto> getProdutoByCategoria(String descricaoCategoria) {
//        List<ProdutoModel> produtosModel = produtoRepository.findByCategoria_Nome(descricaoCategoria);
//        List<Produto> produtos = new ArrayList();
//        produtosModel.forEach(p -> produtos.add(this.produtoAdapter.toDomain(p)));
//        return produtos;
//    }
//}
