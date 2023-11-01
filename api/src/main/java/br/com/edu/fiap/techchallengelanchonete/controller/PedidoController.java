package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.pedido.PedidoAdapterJPA;
import br.com.edu.fiap.techchallengelanchonete.usecase.PedidoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private PedidoUseCase pedidoUseCase;

    @Autowired
    public PedidoController (PedidoAdapterJPA pedidoRepository) {
        this.pedidoUseCase = new PedidoUseCase(pedidoRepository);
    }

    @PostMapping
    public ResponseEntity<Pedido> pedido(@RequestBody Pedido pedido) {
        System.out.println(pedido.toString());
        return ResponseEntity.ok(pedidoUseCase.registraPedido(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> pedidos(@RequestParam(name = "status", required = false) String status) {

        return status == null ?
                ResponseEntity.ok(pedidoUseCase.listaPedidos()) :
                ResponseEntity.ok(pedidoUseCase.listaPedidosPorStatus(status));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizaStatusPedido(@PathVariable Long id, @RequestBody String status) {
        return ResponseEntity.ok(pedidoUseCase.atualizaStatusPedido(id, status));
    }
}
