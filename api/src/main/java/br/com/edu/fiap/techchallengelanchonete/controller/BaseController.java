package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;
import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.usecase.CategoriaUseCase;
import br.com.edu.fiap.techchallengelanchonete.usecase.ProdutoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @Autowired
    ProdutoUseCase useCase;

    @Autowired
    CategoriaUseCase categoriaUseCase;

//    @GetMapping("/teste")
//    public String teste(@RequestParam("id") Integer id)
//    {
//        var categoria = new Categoria();
//        categoria.setNome(new Nome("teste"));
//        categoriaUseCase.salvaCategoria(categoria);
//        var produto = new Produto();
//        produto.setNome(new Nome("Cream cracker"));
//        produto.setCategoria(categoria);
//        useCase.saveProduto(produto);
//
//        var prod = useCase.getProduto(id.longValue());
//        return prod.getNome().getValor();
//    }
}
