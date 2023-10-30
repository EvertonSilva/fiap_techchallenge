package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Pedido {
    private List<ItemPedido> itens;
    private Cliente cliente;
    private Pagamento pagamento;
    private StatusPedido status;

    public Pedido() {
        this.pagamento = new Pagamento(StatusPagamento.AGUARDANDO);
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
        this.itens = new ArrayList<>();
    }

    public Valor getValor() {
        Valor valor = new Valor(new BigDecimal(0));

        for (ItemPedido item: itens) {
            valor.getValor().add(item.getValor().getValor());
        }

        return valor;
    }

    public void confirmaPagamento(StatusPagamento status) {
        this.pagamento.setStatus(status);
    }
}
