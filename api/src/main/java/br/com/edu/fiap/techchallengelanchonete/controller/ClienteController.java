package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ICliente;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ClienteAdpterJPA;
import br.com.edu.fiap.techchallengelanchonete.usecase.ClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteUseCase clienteUseCase;

    @Autowired
    public ClienteController (ClienteAdpterJPA clienteRepository) {
        this.clienteUseCase = new ClienteUseCase(clienteRepository);
    }

    @PostMapping("/clienteSemIdentificacao")
    public ResponseEntity<ICliente> semIdentificacaoCliente()
    {
        var cliente = clienteUseCase.semIdentificacaoCliente();
        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ICliente> cliente(@RequestParam("cpf") String cpf) {
        Cliente cliente = clienteUseCase.buscaClientePorCPF(cpf);

        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ICliente> criaCliente(@RequestBody Cliente cliente)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteUseCase.salvaCliente(cliente));
    }

}
