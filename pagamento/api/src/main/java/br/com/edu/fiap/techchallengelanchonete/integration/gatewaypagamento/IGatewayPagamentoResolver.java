package br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento;

import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;

import java.util.Map;

public interface IGatewayPagamentoResolver {
    boolean validaGatewayPagamentoCorrente(Map<String, String> headers, Map<String, String> params, Map<String, Object> body);
    StatusPagamento interpretaStatusPagamento(Map<String, String> headers, Map<String, String> params, Map<String, Object> body);
}
