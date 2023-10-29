package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.usecase.ProdutoUseCase;
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
