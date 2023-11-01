package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.categoria.CategoriaAdapterJPA;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.produto.ProdutoAdapterJPA;
import br.com.edu.fiap.techchallengelanchonete.usecase.ProdutoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoUseCase produtoUseCase;

    @Autowired
    public ProdutoController(ProdutoAdapterJPA produtoAdapterJPA, CategoriaAdapterJPA categoriaAdapterJPA) {
        this.produtoUseCase = new ProdutoUseCase(produtoAdapterJPA, categoriaAdapterJPA);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
        System.out.println(produto.toString());
        return new ResponseEntity<Produto>
                (produtoUseCase.saveProduto(produto),
                        HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> getAllProdutos(
            @RequestParam(value = "categoria", required = false) String descricaoCategoria) {

        return descricaoCategoria == null ?
                ResponseEntity.ok().body(produtoUseCase.getAllProdutos()) :
                ResponseEntity.ok().body(produtoUseCase.getProdutoByCategoria(descricaoCategoria));
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        var produto = produtoUseCase.getProdutoById(id);

        return produto != null ?
                ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

}
