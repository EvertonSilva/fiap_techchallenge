package br.com.edu.fiap.techchallengelanchonete.domain;

import lombok.Data;

import java.util.List;

@Data
public class Pedido {
    private List<ItemPedido> itens;
    private Cliente cliente;
    private Pagamento pagamento;
    private StatusPedido status;
}
