package br.com.edu.fiap.techchallengelanchonete.infrastructure.pedido;

import br.com.edu.fiap.techchallengelanchonete.adapter.PedidoAdapter;
import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPedidoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoAdapterJPA implements IPedidoPersistence {

    private PedidoRepository pedidoRepository;
    private PedidoAdapter pedidoAdapter;

    @Autowired
    public PedidoAdapterJPA(PedidoRepository pedidoRepository, PedidoAdapter pedidoAdapter) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoAdapter = pedidoAdapter;
    }

    @Override
    public Pedido registraPedido(Pedido pedido) {
        var pedidoModel = this.pedidoAdapter.toModel(pedido);
        pedidoModel.getItens().stream().forEach(x -> x.setPedido(pedidoModel));

        return this.pedidoAdapter.toDomain(this.pedidoRepository.save(pedidoModel));
    }

    @Override
    public List<Pedido> listaPedidos() {
        var pedidosModel = this.pedidoRepository.findAll();
        return pedidosModel.stream().map(x -> this.pedidoAdapter.toDomain(x)).collect(Collectors.toList());
    }
}
