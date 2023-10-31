package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ICliente;
import br.com.edu.fiap.techchallengelanchonete.usecase.ClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ClienteUseCase clienteUseCase;

    @GetMapping("/clienteSemIdentificacao")
    public ResponseEntity<ICliente> semIdentificacaoCliente()
    {
        var cliente = clienteUseCase.semIdentificacaoCliente();
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/autenticaCliente")
    public ResponseEntity<ICliente> autenticaCliente(@RequestBody Map<String,String> map)
    {
        var email = map.get("email");
        var cpf = map.get("cpf");
        var cliente = clienteUseCase.autenticaCliente(cpf, email);

        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
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
        cliente = clienteUseCase.salvaCliente(cliente);
        return ResponseEntity.ok().body(cliente);
    }

}
