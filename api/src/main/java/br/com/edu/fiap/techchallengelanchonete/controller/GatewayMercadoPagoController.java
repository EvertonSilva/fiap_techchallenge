package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoConfirmacao;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoResolver;
import br.com.edu.fiap.techchallengelanchonete.usecase.PedidoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/gatewaypagamento")
public class GatewayMercadoPagoController implements IGatewayPagamentoConfirmacao {
    private PedidoUseCase pedidoUseCase;
    private List<IGatewayPagamentoResolver> gatewayPagamentoResolvers;

    public GatewayMercadoPagoController(PedidoUseCase pedidoUseCase, List<IGatewayPagamentoResolver> gatewayPagamentoResolvers) {
        this.pedidoUseCase = pedidoUseCase;
        this.gatewayPagamentoResolvers = gatewayPagamentoResolvers;
    }

    @PostMapping("pedido/{codigoPedido}")
    public ResponseEntity<Object> confirmacaoPagamento(@PathVariable String codigoPedido,
                                               @RequestHeader Map<String, String> headers,
                                               @RequestParam Map<String, String> params,
                                               @RequestBody Map<String, Object> body) {

        Optional<StatusPagamento> optionalStatusPagamento = Optional.empty();
        for (IGatewayPagamentoResolver gatewayPagamentoResolver: gatewayPagamentoResolvers) {
            if (gatewayPagamentoResolver.validaGatewayPagamentoCorrente(headers, params, body)) {
                optionalStatusPagamento = Optional.of(gatewayPagamentoResolver.interpretaStatusPagamento(headers, params, body));
            }
        }

        optionalStatusPagamento.ifPresent(statusPagamento -> this.confirmacaoPagamento(codigoPedido, statusPagamento));
        return ResponseEntity.ok().build();
    }

    @Override
    public void confirmacaoPagamento(String codigoPedido, StatusPagamento statusPagamento) {
        this.pedidoUseCase.confirmacaoPagamento(codigoPedido, statusPagamento);
    }
}
