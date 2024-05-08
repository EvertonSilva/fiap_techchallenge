package br.com.edu.fiap.techchallengelanchonete.adapter;

import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataExpiracao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoCopiaCola;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoQRCode;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.pagamento.PagamentoModel;
import org.springframework.stereotype.Component;

@Component
public class PagamentoAdapter implements IAdapter<Pagamento, PagamentoModel>{
    @Override
    public Pagamento toDomain(PagamentoModel pagamentoModel) {
        return Pagamento.builder()
                .status(StatusPagamento.valueOf(pagamentoModel.getStatusPagamento()))
                .dataExpiracaoPagamento(new DataExpiracao(pagamentoModel.getDataExpiracaoPagamento()))
                .pixCopiaECola(new PagamentoCopiaCola(pagamentoModel.getPixCopiaECola()))
                .pixQRCode64(new PagamentoQRCode(pagamentoModel.getPixQRCode64()))
                .build();
    }

    @Override
    public PagamentoModel toModel(Pagamento pagamento) {
        return PagamentoModel.builder()
                .statusPagamento(pagamento.getStatus().toString())
                .dataExpiracaoPagamento(pagamento.getDataExpiracaoPagamento().getData())
                .pixCopiaECola(pagamento.getPixCopiaECola().getValor())
                .pixQRCode64(pagamento.getPixQRCode64().getValor())
                .build();
    }
}
