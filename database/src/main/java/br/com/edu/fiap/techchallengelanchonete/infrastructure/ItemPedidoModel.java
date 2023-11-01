package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import br.com.edu.fiap.techchallengelanchonete.infrastructure.pedido.PedidoModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "itens_pedido")
@EqualsAndHashCode(callSuper = true)
public class ItemPedidoModel extends DomainObject {
    @Column
    private int quantidade;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoModel pedido;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoModel produto;
}
