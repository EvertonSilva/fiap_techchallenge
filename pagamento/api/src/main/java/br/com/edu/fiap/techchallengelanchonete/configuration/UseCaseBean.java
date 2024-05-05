package br.com.edu.fiap.techchallengelanchonete.configuration;

import br.com.edu.fiap.techchallengelanchonete.infrastructure.cliente.ClienteAdpterJPA;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.pedido.PedidoAdapterJPA;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.produto.ProdutoAdapterJPA;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoRegistrador;
import br.com.edu.fiap.techchallengelanchonete.usecase.PedidoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBean {
    @Bean
    public PedidoUseCase pedidoUseCase(PedidoAdapterJPA pedidoAdapterJPA, ProdutoAdapterJPA produtoAdapterJPA,
                                       ClienteAdpterJPA clienteAdpterJPA, IGatewayPagamentoRegistrador gatewayPagamentoRegistrador) {
        return new PedidoUseCase(pedidoAdapterJPA, produtoAdapterJPA, clienteAdpterJPA, gatewayPagamentoRegistrador);
    }
}
