package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.usecase.ProdutoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoUseCase useCase;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
        System.out.println(produto.toString());
        return new ResponseEntity<Produto>
                (useCase.saveProduto(produto),
                        HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> getAllProdutos() {
        return ResponseEntity.ok()
                .body(useCase.getAllProdutos());
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(useCase.getProdutoById(id));
    }

    @RequestMapping(value="/categoria", method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> getByCategoria(@RequestParam("categoria") String descricaoCategoria) {
        return ResponseEntity.ok()
                .body(useCase.getProdutoByCategoria(descricaoCategoria));
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id,
                                                 @RequestBody Produto produto) {
        return new ResponseEntity<Produto>
                (useCase.updateProduto(id, produto),
                        HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity updateProduto(@PathVariable Long id) {
        useCase.deleteProduto(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
