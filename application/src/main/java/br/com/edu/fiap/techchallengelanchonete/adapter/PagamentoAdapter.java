package br.com.edu.fiap.techchallengelanchonete.adapter;

import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoAdapter implements IAdapter<Pagamento, String>{
    @Override
    public Pagamento toDomain(String statusPagamento) {
        var pagamento = new Pagamento();

        pagamento.setStatus(StatusPagamento.valueOf(statusPagamento));

        return pagamento;
    }

    @Override
    public String toModel(Pagamento pagamento) {
        return pagamento.getStatus().toString();
    }
}
