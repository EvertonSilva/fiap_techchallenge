package br.com.edu.fiap.techchallengelanchonete.infrastructure.pedido;

import br.com.edu.fiap.techchallengelanchonete.infrastructure.cliente.ClienteModel;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.DomainObject;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.pagamento.PagamentoModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
@EqualsAndHashCode(callSuper = true)
public class PedidoModel extends DomainObject {
    @OneToMany(mappedBy = "pedido", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<ItemPedidoModel> itens;
    @ManyToOne
    private ClienteModel cliente;
    @OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private PagamentoModel pagamento;
    @Column
    private String statusPedido;
    @Column
    private String codigo;

    public PedidoModel() {
        this.itens = new ArrayList<>();
    }
}
