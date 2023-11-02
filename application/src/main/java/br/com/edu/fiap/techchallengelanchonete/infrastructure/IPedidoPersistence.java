package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPedido;

import java.util.List;

public interface IPedidoPersistence {
    Pedido registraPedido(Pedido pedido);
    Pedido pedidoPorId(Long idPedido);
    List<Pedido> listaPedidos();
    List<Pedido> listaPedidosPorStatus(StatusPedido status);
}
