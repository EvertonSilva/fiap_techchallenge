package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPedido;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.cliente.ClienteAdpterJPA;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.pedido.PedidoAdapterJPA;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.produto.ProdutoAdapterJPA;
import br.com.edu.fiap.techchallengelanchonete.usecase.PedidoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private PedidoUseCase pedidoUseCase;

    @Autowired
    public PedidoController (PedidoAdapterJPA pedidoAdapterJPA, ProdutoAdapterJPA produtoAdapterJPA, ClienteAdpterJPA clienteAdpterJPA) {
        this.pedidoUseCase = new PedidoUseCase(pedidoAdapterJPA, produtoAdapterJPA, clienteAdpterJPA);
    }

    @PostMapping
    public ResponseEntity<Pedido> pedido(@RequestBody Pedido pedido) {
        System.out.println(pedido.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoUseCase.registraPedido(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> pedidos(@RequestParam(name = "status", required = false) String status) {
        return status == null ?
                ResponseEntity.ok(pedidoUseCase.listaPedidos()) :
                ResponseEntity.ok(pedidoUseCase.listaPedidosPorStatus(status));
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<Pedido> atualizaStatusPedido(@PathVariable Long id, @PathVariable String status) {
        System.out.println(MessageFormat.format("{0} -> {1}", id, status));
        return ResponseEntity.ok(pedidoUseCase.atualizaStatusPedido(id, status));
    }
}
