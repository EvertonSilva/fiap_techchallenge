package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPedido;

import java.util.List;
import java.util.Optional;

public interface IPedidoPersistence {
    Pedido registraPedido(Pedido pedido);
    Optional<Pedido> pedidoPorId(Long idPedido);
    List<Pedido> listaPedidos();
    List<Pedido> listaPedidosPorStatus(StatusPedido status);
    Optional<Pedido> consultaPedidoPorCodigo(String codigo);
    Pedido atualizarPedido(Pedido pedido);
}
