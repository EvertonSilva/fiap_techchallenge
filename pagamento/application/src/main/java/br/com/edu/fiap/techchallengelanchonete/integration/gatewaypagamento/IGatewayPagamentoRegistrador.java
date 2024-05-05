package br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento;

import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;

public interface IGatewayPagamentoRegistrador {
    void registroPagamento(Pedido pedido) throws ApplicationException;
}
