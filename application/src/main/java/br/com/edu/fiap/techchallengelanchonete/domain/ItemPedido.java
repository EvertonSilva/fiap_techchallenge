package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Quantidade;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ItemPedido extends DomainObject {
    private Produto produto;
    private Quantidade quantidade;

    public ItemPedido() {
        this.quantidade = new Quantidade(0);
        this.produto = new Produto();
    }

    public Valor getSubTotal() {
        var subTotal = new Valor(new BigDecimal(0));

        if (quantidade == null || produto == null)
            return subTotal;

        var multiplicacaoSubTotal = produto.getPreco().getValor().multiply(new BigDecimal(quantidade.getValor()));
        var subTotalArredondado = multiplicacaoSubTotal.setScale(2,  RoundingMode.HALF_DOWN);

        subTotal.setValor(subTotalArredondado);
        return subTotal;
    }
}
