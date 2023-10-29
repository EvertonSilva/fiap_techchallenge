package br.com.edu.fiap.techchallengelanchonete.domain;

import lombok.Data;

@Data
public class ItemPedido {
    private Produto produto;
    private int quantidade;
}
