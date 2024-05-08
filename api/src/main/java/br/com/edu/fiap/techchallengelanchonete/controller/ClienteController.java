package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.cliente.ClienteAdpterJPA;
import br.com.edu.fiap.techchallengelanchonete.usecase.ClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteUseCase clienteUseCase;

    @Autowired
    public ClienteController (ClienteAdpterJPA clienteRepository) {
        this.clienteUseCase = new ClienteUseCase(clienteRepository);
    }

    @GetMapping
    public ResponseEntity<Cliente> cliente(@RequestParam("cpf") String cpf) {
        Cliente cliente = clienteUseCase.buscaClientePorCPF(cpf);

        return cliente instanceof ClienteNulo ?
                ResponseEntity.notFound().build() : ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> criaCliente(@RequestBody Cliente cliente)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteUseCase.salvaCliente(cliente));
    }

}
