package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ICliente;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.usecase.ClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
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
    public ResponseEntity<ICliente> autenticaCliente(@RequestBody Cliente cliente)
    {
        cliente = clienteUseCase.autenticaCliente(cliente);
        return ResponseEntity.ok().body(cliente);
    }

    //@PostMapping("/autenticaCliente2")
    //public ResponseEntity<ICliente> autenticaCliente2(@RequestBody Map<String,String> map)
    //{
    //   var email = map.get("email");
    //    var cpf = map.get("cpf");
    //    var cliente = clienteUseCase.autenticaCliente2(cpf, email);
    //    return ResponseEntity.ok().body(cliente);
    //}

    @PostMapping("/criaCliente")
    public ResponseEntity<ICliente> criaCliente(@RequestBody Cliente cliente)
    {
        cliente = clienteUseCase.salvaCliente(cliente);
        return ResponseEntity.ok().body(cliente);
    }

}
