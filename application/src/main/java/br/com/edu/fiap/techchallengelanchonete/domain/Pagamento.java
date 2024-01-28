package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataExpiracaoPagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoCopiaCola;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoQRCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Pagamento {
    private StatusPagamento status;
    private DataExpiracaoPagamento dataExpiracaoPagamento;
    private PagamentoCopiaCola pixCopiaECola;
    private PagamentoQRCode pixQRCode64;

    public Pagamento() {
        this.status = StatusPagamento.AGUARDANDO;
        this.dataExpiracaoPagamento = new DataExpiracaoPagamento();
        this.pixCopiaECola = new PagamentoCopiaCola();
        this.pixQRCode64 = new PagamentoQRCode();
    }

    public Pagamento(DataExpiracaoPagamento dataExpiracaoPagamento, PagamentoCopiaCola pixCopiaECola, PagamentoQRCode pixQRCode64) {
        this.dataExpiracaoPagamento = dataExpiracaoPagamento;
        this.pixCopiaECola = pixCopiaECola;
        this.pixQRCode64 = pixQRCode64;
    }
}