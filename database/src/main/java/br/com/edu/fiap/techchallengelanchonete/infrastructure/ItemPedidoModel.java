package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "itens_pedido")
public class ItemPedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int quantidade;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoModel pedido;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoModel produto;
}
