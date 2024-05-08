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

    @PostMapping
    public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoUseCase.salvaProduto(produto));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos(
            @RequestParam(value = "categoria", required = false) String descricaoCategoria) {
        return ResponseEntity
                .ok()
                .body(produtoUseCase.listaProdutos(descricaoCategoria));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        var produto = produtoUseCase.buscaPorId(id);

        return produto
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
