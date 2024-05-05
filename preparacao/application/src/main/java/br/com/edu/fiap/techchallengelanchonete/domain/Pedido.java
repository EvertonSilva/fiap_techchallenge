package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataCriacao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        this.data = new DataCriacao();

        this.pagamento = new Pagamento();
        this.cliente = new Cliente();
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
        this.itens = new ArrayList<>();
    }

    public static List<Pedido> ordenarListagem(List<Pedido> pedidos) {
        List<Pedido> pedidosOrdenados = new ArrayList<>(pedidos);

        pedidosOrdenados = ordenaPorStatus(pedidosOrdenados);
        pedidosOrdenados = ordenaPorDataMaisAntiga(pedidosOrdenados);
        pedidosOrdenados = filtraPorStatus(pedidosOrdenados);

        return pedidosOrdenados;
    }

    public static List<Pedido> ordenaPorStatus(List<Pedido> pedidos) {
        List<Pedido> pedidosOrdenados = new ArrayList<>(pedidos);

        pedidosOrdenados
            .sort(Comparator.comparing(
                (pedido) -> {
                    switch (pedido.getStatus()) {
                        case RECEBIDO: return 0;
                        case EM_PREPARACAO: return 1;
                        case PRONTO: return 2;
                        default: return 3;
                    }
                })
            );

        return pedidosOrdenados;
    }

    public static List<Pedido> ordenaPorDataMaisAntiga(List<Pedido> pedidos) {
        List<Pedido> pedidosOrdenados = new ArrayList<>(pedidos);

        pedidosOrdenados.sort(
            Comparator.comparing(
                (pedido) -> pedido.getData().getValor().getTime()));

        return pedidosOrdenados;
    }
    
    public static List<Pedido> filtraPorStatus(List<Pedido> pedidos) {
        List<Pedido> pedidosFiltrados =
            pedidos
                .stream()
                .filter(pedido -> 
                    pedido.getStatus() != StatusPedido.FINALIZADO 
                    && pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO)
                .collect(Collectors.toList());
        
        return pedidosFiltrados;
    }

    public Valor getValorTotal() {
        Valor valorTotal = new Valor(new BigDecimal(0));

        for (ItemPedido item: itens) {
            var subTotal = item.getSubTotal().getValor();
            var valorTotalAtualizado = valorTotal.getValor().add(subTotal);
            valorTotal.setValor(valorTotalAtualizado);
        }

        return valorTotal;
    }

    public boolean validaProximoStatus(StatusPedido status) {
        var recebidoValido =
                this.status == StatusPedido.AGUARDANDO_PAGAMENTO
                        && this.getPagamento().getStatus() == StatusPagamento.APROVADO
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
        var canceladoValido =
                (this.status == StatusPedido.AGUARDANDO_PAGAMENTO
                    || this.status == StatusPedido.RECEBIDO
                    || this.status == StatusPedido.EM_PREPARACAO
                    || this.status == StatusPedido.PRONTO)
                        && status == StatusPedido.CANCELADO;

        return recebidoValido || emPreparacaoValido || prontoValido || finalizadoValido || canceladoValido;
    }
}