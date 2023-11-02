package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.domain.ItemPedido;
import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPedido;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IClientePersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPedidoPersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IProdutoPersistence;

import java.util.List;

public class PedidoUseCase {
    private IPedidoPersistence pedidoPersistence;
    private IProdutoPersistence produtoPersistence;
    private IClientePersistence clientePersistence;

    public PedidoUseCase(IPedidoPersistence pedidoPersistence, IProdutoPersistence produtoPersistence, IClientePersistence clientePersistence) {
        this.pedidoPersistence = pedidoPersistence;
        this.produtoPersistence = produtoPersistence;
        this.clientePersistence = clientePersistence;
    }

    public Pedido registraPedido(Pedido pedido) {
        if (!protudosExistentes(pedido))
            throw new ApplicationException("Produto(s) inexistente(s)!");
        if (pedido.getCliente() != null && pedido.getCliente().getId() != null)
        {
            var clienteExistente = this.clientePersistence.buscaId(pedido.getCliente().getId().getValor());
            if (clienteExistente instanceof ClienteNulo)
                throw new ApplicationException("Cliente inexistente!");
        }

        if (pedido.getCliente() == null)
            pedido.setCliente(new ClienteNulo());

        return this.pedidoPersistence.registraPedido(pedido);
    }

    public List<Pedido> listaPedidos() {
        return this.pedidoPersistence.listaPedidos();
    }

    public List<Pedido> listaPedidosPorStatus(String status) {
        StatusPedido statusPedido = getStatusPedido(status);
        return pedidoPersistence.listaPedidosPorStatus(statusPedido);
    }

    public Pedido atualizaStatusPedido(Long idPedido, String status) {
        var pedido = pedidoPersistence.pedidoPorId(idPedido);
        StatusPedido statusPedido = getStatusPedido(status);

        if (!pedido.validaProximoStatus(statusPedido))
            throw new ApplicationException("Status incoerente!");

        pedido.setStatus(statusPedido);
        pedidoPersistence.registraPedido(pedido);

        return pedido;
    }

    private StatusPedido getStatusPedido(String status) {
        try {
            return Enum.valueOf(StatusPedido.class, status.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw  new ApplicationException("Status não existe");
        }
    }

    private boolean protudosExistentes(Pedido pedido) {
        var produtosExistentes =
                !pedido.getItens().isEmpty()
                        && pedido.getItens().stream().allMatch(x -> x.getProduto() != null);

        for (ItemPedido item: pedido.getItens()) {
            var produtoBuscado = this.produtoPersistence.buscaId(item.getProduto().getId().getValor());
            produtosExistentes &= produtoBuscado.isPresent();
        }

        return produtosExistentes;
    }
}
