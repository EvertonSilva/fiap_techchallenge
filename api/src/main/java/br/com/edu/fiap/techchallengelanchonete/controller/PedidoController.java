package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPedido;
import br.com.edu.fiap.techchallengelanchonete.usecase.PedidoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private PedidoUseCase pedidoUseCase;

    public PedidoController (PedidoUseCase pedidoUseCase) {
        this.pedidoUseCase = pedidoUseCase;
    }

    @PostMapping
    public ResponseEntity<Pedido> pedido(@RequestBody Pedido pedido) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoUseCase.registraPedido(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> pedidos(@RequestParam(name = "status", required = false) String statusPedido) {
        Optional<StatusPedido> status = statusPedido != null ?
            Optional.of(StatusPedido.de(statusPedido)) : Optional.empty();

        return ResponseEntity
                .ok(pedidoUseCase.listaPedidos(status));
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<Pedido> pedido(@PathVariable Long id) {
        var pedido = pedidoUseCase.buscaPorId(id);

        return pedido
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<Pedido> atualizaStatusPedido(@PathVariable Long id, @PathVariable String status) {
        return ResponseEntity.ok(pedidoUseCase.atualizaStatusPedido(id, StatusPedido.de(status)));
    }
}
