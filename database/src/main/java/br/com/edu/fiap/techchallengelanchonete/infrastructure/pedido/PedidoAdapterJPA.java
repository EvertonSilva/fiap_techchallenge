package br.com.edu.fiap.techchallengelanchonete.infrastructure.pedido;

import br.com.edu.fiap.techchallengelanchonete.adapter.PedidoAdapter;
import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPedido;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPedidoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoAdapterJPA implements IPedidoPersistence {

    private PedidoRepository repository;
    private PedidoAdapter adapter;

    @Autowired
    public PedidoAdapterJPA(PedidoRepository pedidoRepository, PedidoAdapter pedidoAdapter) {
        this.repository = pedidoRepository;
        this.adapter = pedidoAdapter;
    }

    @Override
    public Pedido registraPedido(Pedido pedido) {
        var pedidoModel = this.adapter.toModel(pedido);
        pedidoModel.getItens().stream().forEach(x -> x.setPedido(pedidoModel));

        return this.adapter.toDomain(this.repository.save(pedidoModel));
    }

    @Override
    public Pedido pedidoPorId(Long idPedido) {
        var pedidoModel = this.repository.findById(idPedido).orElse(null);
        return this.adapter.toDomain(pedidoModel);
    }

    @Override
    public List<Pedido> listaPedidos() {
        var pedidosModel = this.repository.findAll();
        return pedidosModel.stream().map(x -> this.adapter.toDomain(x)).collect(Collectors.toList());
    }

    @Override
    public List<Pedido> listaPedidosPorStatus(StatusPedido status) {
        var pedidosModel = this.repository.findByStatusPedido(status.name());

        return pedidosModel.stream()
                .map(model -> this.adapter.toDomain(model))
                .collect(Collectors.toList());
    }
}
