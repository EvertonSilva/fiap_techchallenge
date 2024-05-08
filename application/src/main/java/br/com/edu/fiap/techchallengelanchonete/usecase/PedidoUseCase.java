package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.*;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.exception.NotFoundResourceException;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IClientePersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPedidoPersistence;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IProdutoPersistence;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoRegistrador;

import java.util.List;
import java.util.Optional;

public class PedidoUseCase {
    private IPedidoPersistence pedidoPersistence;
    private IProdutoPersistence produtoPersistence;
    private IClientePersistence clientePersistence;
    private IGatewayPagamentoRegistrador gatewayPagamentoRegistrador;

    public PedidoUseCase(IPedidoPersistence pedidoPersistence, IProdutoPersistence produtoPersistence, IClientePersistence clientePersistence,
                         IGatewayPagamentoRegistrador gatewayPagamentoRegistrador) {
        this.pedidoPersistence = pedidoPersistence;
        this.produtoPersistence = produtoPersistence;
        this.clientePersistence = clientePersistence;
        this.gatewayPagamentoRegistrador = gatewayPagamentoRegistrador;
    }

    public Pedido registraPedido(Pedido pedido) {
        if (!protudosExistentes(pedido))
            throw new ApplicationException("Produto(s) inexistente(s)!");

        pedido.getItens().forEach(itemPedido -> {
            var optionalProduto = this.produtoPersistence.buscaId(itemPedido.getProduto().getId().getValor());
            itemPedido.setProduto(optionalProduto.orElseThrow(() -> new NotFoundResourceException("Produto não encontrado!")));
        });

        if (pedido.getCliente() != null 
            && pedido.getCliente().getId() != null 
            && pedido.getCliente().getId().getValor() > 0)
        {
            var clienteExistente = this.clientePersistence.buscaId(pedido.getCliente().getId().getValor());
            if (clienteExistente instanceof ClienteNulo)
                throw new NotFoundResourceException("Cliente não encontrado!");

            pedido.setCliente(clienteExistente);
        } else {
            pedido.setCliente(new ClienteNulo());
        }

        this.gatewayPagamentoRegistrador.registroPagamento(pedido);
        return this.pedidoPersistence.registraPedido(pedido);
    }

    public Pedido atualizaPedido(Pedido pedido) {
        return this.pedidoPersistence.atualizarPedido(pedido);
    }

    public Optional<Pedido> consultaPedido(String codigoPedido) {
        return pedidoPersistence.consultaPedidoPorCodigo(codigoPedido);
    }

    public void confirmacaoPagamento(String codigoPedido, StatusPagamento statusPagamento) {
        var optionalPedido = this.consultaPedido(codigoPedido);

        if (!optionalPedido.isPresent())
            throw new NotFoundResourceException("Pedido não encontrado!");

        var pedido = optionalPedido.get();
        pedido.getPagamento().setStatus(statusPagamento);

        if (statusPagamento == StatusPagamento.APROVADO)
            pedido.setStatus(StatusPedido.RECEBIDO);
        else
            pedido.setStatus(StatusPedido.CANCELADO);

        this.atualizaPedido(pedido);
    }

    public Optional<Pedido> buscaPorId(Long idPedido) {
        return pedidoPersistence.pedidoPorId(idPedido);
    }

    public List<Pedido> listaPedidos(Optional<StatusPedido> status) {
        return status
                .map(s -> pedidoPersistence.listaPedidosPorStatus(s))
                .orElseGet(() -> {
                    List<Pedido> pedidos = pedidoPersistence.listaPedidos();
                    return Pedido.ordenaPorStatusDataCriacaoFiltraPorStatus(pedidos);
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
        return pedido.getItens() != null
            && !pedido.getItens().isEmpty()
            && pedido.getItens().stream().allMatch(x -> x.getProduto() != null);
    }
}
