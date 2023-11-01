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
            StatusPedido statusPedido = getStatusPedido(status);
            return pedidoRepository.listaPedidosPorStatus(statusPedido);
        } catch (IllegalArgumentException ex) {
            throw  new RuntimeException("Status não existe");
        }
    }

    public Pedido atualizaStatusPedido(Long idPedido, String status) {
        try {
            var pedido = pedidoRepository.pedidoPorId(idPedido);
            StatusPedido statusPedido = getStatusPedido(status);

            // TODO validar se pedido pode "avançar" para o status recebido
            pedido.setStatus(statusPedido);
            pedidoRepository.registraPedido(pedido);

            return pedido;
        } catch (IllegalArgumentException ex) {
            throw  new RuntimeException("Status não existe");
        }
    }

    private StatusPedido getStatusPedido(String status) {
        return Enum.valueOf(StatusPedido.class, status.toUpperCase());
    }
}
