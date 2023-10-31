package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Categoria;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.usecase.ClienteUseCase;
import br.com.edu.fiap.techchallengelanchonete.usecase.ProdutoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @Autowired
    ProdutoUseCase useCase;

    @Autowired
    ClienteUseCase clienteUseCase;

    @GetMapping("/cliente")
    public String salvaCliente()
    {
        var cliente = clienteUseCase.semIdentificacaoCliente(new Cliente());

        return cliente.getNome().getValor();
    }

    @PostMapping("/autenticaCliente")
    public ResponseEntity<Cliente> buscaCliente()
    {
        var cliente = new Cliente(new Email("autentica@cliente"), new CPF("11111111122"));
        cliente = clienteUseCase.autenticaCliente(cliente);

        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> testeCliente()
    {
        var cliente = new Cliente(new Nome("Nome cliente 001"), new Email("salva@cliente"), new CPF( "11111111111"));
        cliente = clienteUseCase.salvaCliente(cliente);

        return ResponseEntity.ok().body(cliente);
    }
}
