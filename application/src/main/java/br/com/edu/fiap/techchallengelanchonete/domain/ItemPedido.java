package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Quantidade;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedido {
    private Produto produto;
    private Quantidade quantidade;

    public ItemPedido() {
        this.quantidade = new Quantidade(0);
    }

    public Valor getValor() {
        return new Valor(produto.getPreco().multiply(new BigDecimal(quantidade.getValor())));
    }
}
