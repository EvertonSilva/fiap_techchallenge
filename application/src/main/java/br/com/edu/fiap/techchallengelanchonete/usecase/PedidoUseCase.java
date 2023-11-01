package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPedido;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPedidoPersistence;

import java.util.List;

public class PedidoUseCase {
    private IPedidoPersistence pedidoRepository;

    public PedidoUseCase(IPedidoPersistence pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido registraPedido(Pedido pedido) {
        return this.pedidoRepository.registraPedido(pedido);
    }

    public List<Pedido> listaPedidos() {
        return this.pedidoRepository.listaPedidos();
    }

    public List<Pedido> listaPedidosPorStatus(String status) {
        try {
            StatusPedido statusPedido = Enum.valueOf(StatusPedido.class, status);
            return pedidoRepository.listaPedidosPorStatus(statusPedido);
        } catch (IllegalArgumentException ex) {
            throw  new RuntimeException("Status não existe");
        }
    }
}
