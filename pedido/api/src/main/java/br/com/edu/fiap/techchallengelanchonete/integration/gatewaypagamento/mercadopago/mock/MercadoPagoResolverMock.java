package br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.mercadopago.mock;

import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoResolver;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Map;

// TODO: Descomentar após definições claras dos profiles x contextos de execução da app.
//@Profile("dev")
@Primary
@Component
public class MercadoPagoResolverMock implements IGatewayPagamentoResolver {
    @Override
    public boolean validaGatewayPagamentoCorrente(Map<String, String> headers, Map<String, String> params, Map<String, Object> body) {
        return true;
    }

    @Override
    public StatusPagamento interpretaStatusPagamento(Map<String, String> headers, Map<String, String> params, Map<String, Object> body) {
        var statusMock = headers.get("status-mock");
        return StatusPagamento.valueOf(statusMock);
    }
}
