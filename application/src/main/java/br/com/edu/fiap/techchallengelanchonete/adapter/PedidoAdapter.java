package br.com.edu.fiap.techchallengelanchonete.adapter;

import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPedido;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.PedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PedidoAdapter implements IAdapter<Pedido, PedidoModel> {

    private ClienteAdapter clienteAdpter;
    private PagamentoAdapter pagamentoAdapter;
    private ItemPedidoAdapter itemPedidoAdapter;

    @Autowired
    public PedidoAdapter(ClienteAdapter clienteAdpter, PagamentoAdapter pagamentoAdapter, ItemPedidoAdapter itemPedidoAdapter) {
        this.clienteAdpter = clienteAdpter;
        this.pagamentoAdapter = pagamentoAdapter;
        this.itemPedidoAdapter = itemPedidoAdapter;
    }

    @Override
    public Pedido toDomain(PedidoModel pedidoModel) {
        var pedido = new Pedido();

        pedido.setCliente(clienteAdpter.toDomain(pedidoModel.getCliente()));
        pedido.setStatus(StatusPedido.valueOf(pedidoModel.getStatusPedido()));
        pedido.setPagamento(pagamentoAdapter.toDomain(pedidoModel.getStatusPagamento()));
        pedido.setItens(pedidoModel.getItens().stream().map(x -> itemPedidoAdapter.toDomain(x)).collect(Collectors.toList()));

        return pedido;
    }

    @Override
    public PedidoModel toModel(Pedido pedido) {
        var pedidoModel = new PedidoModel();

        pedidoModel.setCliente(clienteAdpter.toModel(pedido.getCliente()));
        pedidoModel.setStatusPedido(pedido.getStatus().toString());
        pedidoModel.setStatusPagamento(pagamentoAdapter.toModel(pedido.getPagamento()));
        pedidoModel.setItens(pedido.getItens().stream().map(x -> itemPedidoAdapter.toModel(x)).collect(Collectors.toList()));

        return pedidoModel;
    }
}
