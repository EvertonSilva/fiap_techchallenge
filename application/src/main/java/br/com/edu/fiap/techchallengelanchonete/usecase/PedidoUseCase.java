package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.*;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.exception.NotFoundResourceException;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IClientePersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPedidoPersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IProdutoPersistence;

import java.util.List;
import java.util.Optional;

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

    public Optional<Pedido> buscaPorId(Long idPedido) {
        return pedidoPersistence.pedidoPorId(idPedido);
    }
    public List<Pedido> listaPedidos(Optional<StatusPedido> status) {
        return status
                .map(s -> pedidoPersistence.listaPedidosPorStatus(s))
                .orElseGet(() -> {
                    List<Pedido> pedidos = pedidoPersistence.listaPedidos();
                    return Pedido.ordenarListagem(pedidos);
                });
    }

    public Pedido atualizaStatusPedido(Long idPedido, StatusPedido status) {
        var optionalPedido = pedidoPersistence.pedidoPorId(idPedido);

        if (!optionalPedido.isPresent())
            throw new NotFoundResourceException("Pedido não encontrado!");

        var pedido = optionalPedido.get();
        if (!pedido.validaProximoStatus(status))
            throw new ApplicationException("Status incoerente!");

        pedido.setStatus(status);
        pedidoPersistence.registraPedido(pedido);

        return pedido;
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
