package br.com.edu.fiap.techchallengelanchonete.adapter;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPedido;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataCriacao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.cliente.ClienteModel;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.pedido.PedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PedidoAdapter implements IAdapter<Pedido, PedidoModel> {

    private ClienteAdapter clienteAdpter;
    private PagamentoAdapter pagamentoAdapter;
    private ItemPedidoAdapter itemPedidoAdapter;

    @Autowired
    public PedidoAdapter(PagamentoAdapter pagamentoAdapter, ItemPedidoAdapter itemPedidoAdapter) {
        this.clienteAdpter = new ClienteAdapter();
        this.pagamentoAdapter = pagamentoAdapter;
        this.itemPedidoAdapter = itemPedidoAdapter;
    }

    @Override
    public Pedido toDomain(PedidoModel pedidoModel) {
        if (pedidoModel == null)
            return null;

        var pedido = new Pedido();

        pedido.setId(new Id(pedidoModel.getId()));
        pedido.setCliente(clienteAdpter.toDomain(pedidoModel.getCliente()));
        pedido.setStatus(StatusPedido.valueOf(pedidoModel.getStatusPedido()));
        pedido.setPagamento(pagamentoAdapter.toDomain(pedidoModel.getPagamento()));
        pedido.setItens(pedidoModel.getItens().stream().map(x -> itemPedidoAdapter.toDomain(x)).collect(Collectors.toList()));
        pedido.setCodigo(new Codigo(pedidoModel.getCodigo()));
        pedido.setData(new DataCriacao(pedidoModel.getDataCriacao()));

        return pedido;
    }

    @Override
    public PedidoModel toModel(Pedido pedido) {
        if (pedido == null)
            return null;

        var pedidoModel = new PedidoModel();

        if (pedido.getId() != null && pedido.getId().getValor() > 0)
            pedidoModel.setId(pedido.getId().getValor());

        ClienteModel clienteModel = null;
        if (!(pedido.getCliente() instanceof ClienteNulo))
            clienteModel = clienteAdpter.toModel(pedido.getCliente());

        pedidoModel.setCliente(clienteModel);
        pedidoModel.setStatusPedido(pedido.getStatus().toString());
        pedidoModel.setPagamento(pagamentoAdapter.toModel(pedido.getPagamento()));
        pedidoModel.setItens(pedido.getItens().stream().map(x -> itemPedidoAdapter.toModel(x)).collect(Collectors.toList()));
        pedidoModel.setCodigo(pedido.getCodigo().getValor());
        pedidoModel.setDataCriacao(pedido.getData().getValor());

        return pedidoModel;
    }
}
