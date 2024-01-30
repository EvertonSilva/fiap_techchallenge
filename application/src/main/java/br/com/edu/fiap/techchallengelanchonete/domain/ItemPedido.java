package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Quantidade;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ItemPedido extends DomainObject {
    private Produto produto;
    private Quantidade quantidade;

    public ItemPedido() {
        this.quantidade = new Quantidade(0);
    }

    public Valor getSubTotal() {
        return new Valor(produto.getPreco().getValor().multiply(new BigDecimal(quantidade.getValor())));
    }
}
