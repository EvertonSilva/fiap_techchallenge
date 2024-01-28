package br.com.edu.fiap.techchallengelanchonete.infrastructure.pagamento;

import br.com.edu.fiap.techchallengelanchonete.adapter.PagamentoAdapter;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPagamentoPersistence;
import org.springframework.stereotype.Component;

@Component
public class PagamentoAdapterJPA implements IPagamentoPersistence {
    private PagamentoRepository repository;
    private PagamentoAdapter adapter;

    public PagamentoAdapterJPA(PagamentoRepository repository, PagamentoAdapter adapter) {
        this.repository = repository;
        this.adapter = adapter;
    }


}
