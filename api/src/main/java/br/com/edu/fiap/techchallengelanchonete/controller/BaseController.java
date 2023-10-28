package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.app.ProdutoUseCase;
import br.com.edu.fiap.domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @Autowired
    ProdutoUseCase useCase;

    @GetMapping("/teste")
    public String teste()
    {
        var produto = new Produto();
        produto.setNome("Cream cracker");
        useCase.salvaProduto(produto);

        var prod = useCase.getProduto(1L);
        return prod.getNome();
    }
}
