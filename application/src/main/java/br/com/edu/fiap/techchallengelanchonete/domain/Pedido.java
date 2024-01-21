package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataCriacao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Pedido extends DomainObject {
    private List<ItemPedido> itens;
    private Cliente cliente;
    private Pagamento pagamento;
    private StatusPedido status;
    private Codigo codigo;
    private DataCriacao data;

    public Pedido() {
        this.codigo = new Codigo();
        this.codigo.gerarCodigo();

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

    public boolean validaProximoStatus(StatusPedido status) {
        var recebidoValido =
                this.status == StatusPedido.AGUARDANDO_PAGAMENTO
                        && status == StatusPedido.RECEBIDO;
        var emPreparacaoValido =
                this.status == StatusPedido.RECEBIDO
                        && status == StatusPedido.EM_PREPARACAO;
        var prontoValido =
                this.status == StatusPedido.EM_PREPARACAO
                        && status == StatusPedido.PRONTO;
        var finalizadoValido =
                this.status == StatusPedido.PRONTO
                        && status == StatusPedido.FINALIZADO;

        return recebidoValido || emPreparacaoValido || prontoValido || finalizadoValido;
    }

    public void confirmaPagamento(StatusPagamento status) {
        this.pagamento.setStatus(status);
    }
}
