package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "pedido", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<ItemPedidoModel> itens;
    @ManyToOne
    private ClienteModel cliente;
    @Column
    private String statusPagamento;
    @Column
    private String statusPedido;

    public PedidoModel() {
        this.itens = new ArrayList<>();
    }
}
