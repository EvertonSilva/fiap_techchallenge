package br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento;

import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;

public interface IGatewayPagamentoConfirmacao {
    void confirmacaoPagamento(String codigoPedido, StatusPagamento statusPagamento);
}
