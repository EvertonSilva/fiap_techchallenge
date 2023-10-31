package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.usecase.ClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteController {
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

    @PostMapping("/criaCliente")
    public ResponseEntity<Cliente> criaCliente(@RequestBody Cliente cliente)
    {
        //var cliente = new Cliente(new Nome("Nome cliente 001"), new Email("salva@cliente"), new CPF( "11111111111"));
        cliente = clienteUseCase.salvaCliente(cliente);

        return ResponseEntity.ok().body(cliente);
    }

}
